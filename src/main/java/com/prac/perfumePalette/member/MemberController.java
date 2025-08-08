package com.prac.perfumePalette.member;

import com.prac.perfumePalette.Alert;
import com.prac.perfumePalette.order.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService mService;

    //============ 회원가입 =================
    @GetMapping("/enroll")
    public ModelAndView enroll(ModelAndView mv) {
        mv.setViewName("member/enroll");
        return mv;
    }
    @PostMapping("/enroll")
    public ModelAndView enroll(ModelAndView mv,
                               @ModelAttribute Member member,
                               @RequestParam("memberDetailAddr") String memberDetailAddr,
                               @RequestParam("postcode") String postcode) {
        try {
            // 주소 문자열 조합 후 세팅
            // 주소 조합 문자열 가독성 높이기
            String fullAddr = member.getMemberAddr() + " / " + memberDetailAddr + "(" + postcode + ")";
            member.setMemberAddr(fullAddr);

            int result = mService.insertMember(member);

            if (result > 0) {
                Alert alert = new Alert("/member/login", "회원가입에 성공했습니다");
                mv.addObject("alert", alert);
                mv.setViewName("common/alert");
            } else {
                Alert alert = new Alert("/member/enroll", "회원가입에 실패했습니다");
                mv.addObject("alert", alert);
                mv.setViewName("common/alert");
            }
        } catch (Exception e) {
            e.printStackTrace();
            mv.addObject("msg", e.getMessage());
            mv.setViewName("common/error");
        }
        return mv;
    }

    //============ 유효성검사 =================
    @PostMapping(value = "/idChk", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public int idCheck(@RequestParam String memberId) {
        int result = mService.checkId(memberId);
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{4,20}$");
        Matcher matcher = pattern.matcher(memberId);

        if ((memberId.length() < 4 || memberId.length() > 20) || !matcher.matches()) {
            return -2;
        }
        return result;
    }
    @PostMapping(value = "/nicknameChk", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public int nickNameCheck(@RequestParam String memberNickname) {
        int result = mService.checkNickname(memberNickname);
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9가-힣]{2,20}$");
        Matcher matcher = pattern.matcher(memberNickname);

        if ((memberNickname.length() < 2 || memberNickname.length() > 20) || !matcher.matches()) {
            return -2;
        }
        return result;
    }
    @PostMapping(value = "/pwChk", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public int pwCheck(@RequestParam String memberPw, @RequestParam String reMemberPw) {
        int result = 0;
        if (!memberPw.equals(reMemberPw)) {
            result = 1;
        }

        Pattern pattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9]).{8,20}$");
        Matcher matcher = pattern.matcher(String.valueOf(memberPw));

        if (memberPw.length() < 8 || reMemberPw.length() > 20 || !matcher.matches()) {
            return -2;
        }
        return result;
    }
    @PostMapping(value = "/emailChk", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public int emailCheck(@RequestParam String memberEmail) {
        int result = mService.checkEmail(memberEmail);

        // @ 앞에 올 수 있는 문자들 (영문, 숫자, +, _, ., - 허용)
        // @ 뒤 도메인 (영문, 숫자, . , - 허용)
        Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        Matcher matcher = pattern.matcher(memberEmail);

        if (memberEmail.length() >= 50) {
            return -2;

        } else if (!matcher.matches()) {
            return -3;
        }
        return result;
    }

    //============ 로그인 =================
    @GetMapping("/login")
    public ModelAndView login(ModelAndView mv, HttpSession session) {

        // 로그인한 사용자인지 확인
        Member loginUser = (Member) session.getAttribute("member");

        // 이미 로그인된 경우: 로그인 페이지에 직접접근 막기
        if (loginUser != null) {
            mv.setViewName("member/loginBlock");
            return mv;
        }

        // 로그인 뷰
        mv.setViewName("member/login");
        return mv;
    }
    @PostMapping("/login")
    public ModelAndView login(ModelAndView mv, HttpSession session,
                              @ModelAttribute Member member) {
        try {
            // 입력받은 아이디와 비밀번호로 회원 조회
            Member loginUser = mService.login(member);

            if (loginUser == null) {
                // 아이디 또는 비밀번호 불일치
                Alert alert = new Alert("/member/login", "아이디 또는 비밀번호를 다시 확인해주세요.");
                mv.addObject("alert", alert);
                mv.setViewName("common/alert");

            } else if (loginUser.getMemberStatus() != 1) {
                // 탈퇴한 회원
                Alert alert = new Alert("/member/login", "탈퇴한 회원입니다.");
                mv.addObject("alert", alert);
                mv.setViewName("common/alert");

            } else {
                // 로그인 성공 → 세션에 저장
                // memberNo, memberId, memberNickname, memberName 들어있음
                session.setAttribute("member", loginUser);

                // mbti 결과가 없으면 홈으로, 있으면 결과 페이지로 이동
                if (session.getAttribute("mbtiResult") == null) {
                    mv.setViewName("redirect:/"); // 홈으로
                } else {
                    mv.setViewName("redirect:/mbti/mbtiResult"); // MBTI 결과 페이지로
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            mv.addObject("msg", e.getMessage());
            mv.setViewName("common/error");
        }
        return mv;
    }

    //============ 로그아웃 =================
    @GetMapping("logout")
    public ModelAndView logout(HttpServletRequest request, ModelAndView mv) {
        try {
            // 기존 세션 가져오기(없으면 null 반환해서 불필요한 세션 생성 방지)
            HttpSession session = request.getSession(false);

            // 세션이 존재하면 무효화
            if (session != null) {
                session.invalidate();
            }

            // 공통 alert 객체로 로그아웃 알림 후 메인으로 이동
            Alert alert = new Alert("/", "정상적으로 로그아웃되었습니다.");
            mv.addObject("alert", alert);
            mv.setViewName("common/alert");

        } catch (Exception e) {
            e.printStackTrace();
            mv.addObject("msg", e.getMessage());
            mv.setViewName("common/error");
        }
        return mv;
    }

    //============ 아이디 찾기 =================
    @GetMapping("/findId")
    public ModelAndView findId(ModelAndView mv, HttpSession session) {
        // 아이디 찾기 뷰 띄우기
        mv.setViewName("member/findId");

        // 회원정보가져오기
        Member loginUser = (Member) session.getAttribute("member");

        // 이미 로그인된 경우 직접접근 막기
        if (loginUser != null) {
            Alert alert = new Alert("/", "이미 로그인 된 상태입니다.");
            mv.addObject("alert", alert);
            mv.setViewName("common/alert");
            return mv;
        }
        return mv;
    }
    @PostMapping("/findId")
    public ModelAndView findId(ModelAndView mv, @ModelAttribute Member member) {

        try {
            // member 정보를 기반으로 아이디 찾기
            // memberName, memberEmail or memberPhone이 담겨있음
            Member getUser = mService.findId(member);

            // 일치하는 정보가 없을 때
            if (getUser == null) {
                Alert alert = new Alert("/member/findId", "일치하는 정보가 존재하지 않습니다.");
                mv.addObject("alert", alert);
                mv.setViewName("common/alert");
                return mv;
            }

            // 일치하는 회원이 있으면 결과 페이지로 이동
            mv.addObject("member", getUser);
            mv.setViewName("member/showId");
            return mv;

        } catch (Exception e) {
            e.printStackTrace();
            mv.addObject("msg", e.getMessage());
            mv.setViewName("common/error");
        }
        return mv;
    }

    //============ 비밀번호 찾기 =================
    @GetMapping("/findPw")
    public ModelAndView findPw(ModelAndView mv, HttpSession session) {
        // 비밀번호 찾기 뷰 띄우기
        mv.setViewName("member/findPw");

        // 회원정보가져오기
        Member loginUser = (Member) session.getAttribute("member");

        // 이미 로그인된 경우 직접접근 막기
        if (loginUser != null) {
            Alert alert = new Alert("/", "이미 로그인 된 상태입니다.");
            mv.addObject("alert", alert);
            mv.setViewName("common/alert");
            return mv;
        }
        return mv;
    }
    @PostMapping("findPw")
    public ModelAndView findPw(@ModelAttribute Member member, ModelAndView mv) {

        try {
            // member 정보 찾기
            // 아이디, 이름, 이메일 or 전화번호가 담겨있음
            Member getUser = mService.findPw(member);

            if (getUser != null) {
                // 일치하는 회원이 있으면 비밀번호 변경 페이지로 이동
                mv.addObject("member", getUser);
                mv.setViewName("member/changePw");

            } else {
                // 일치하는 회원이 없으면 알람띄우기
                Alert alert = new Alert("/member/findPw", "일치하는 회원 정보가 존재하지 않습니다.");
                mv.addObject("alert", alert);
                mv.setViewName("common/alert");
            }
        } catch (Exception e) {
            e.printStackTrace();
            mv.addObject("msg", e.getMessage());
            mv.setViewName("common/error");
        }
        return mv;
    }

    //============ 비밀번호 변경 =================
    @PostMapping("/changePw")
    public ModelAndView changePw(ModelAndView mv, @ModelAttribute Member member,
                                 String newPw, String confirmPw) {

        try {
            // 비밀번호 유효성 체크
            String pwPattern = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,20}$";
            boolean matchesPw = newPw.matches(pwPattern);

            // 비밀번호와 비밀번호 확인값이 다를 경우
            if (!newPw.equals(confirmPw)) {
                Alert alert = new Alert("/member/findPw", "비밀번호 확인이 일치하지 않습니다.");
                mv.addObject("alert", alert);
                mv.setViewName("common/alert");
            }

            // 비밀번호 설정 후 업데이트
            member.setMemberPw(newPw);
            int result = mService.updatePw(member);

            // 비밀번호 변경 성공 여부에 따른 알람 처리
            if (result > 0) {
                Alert alert = new Alert("/member/login", "비밀번호 변경에 성공하였습니다.");
                mv.addObject("alert", alert);
                mv.setViewName("common/alert");
            } else {
                Alert alert = new Alert("/member/findPw", "비밀번호 변경에 실패했습니다. 다시 시도해주세요.");
                mv.addObject("alert", alert);
                mv.setViewName("common/alert");
            }

        } catch (Exception e) {
            e.printStackTrace();
            mv.addObject("msg", e.getMessage());
            mv.setViewName("common/error");
        }
        return mv;
    }

    //============ 주문내역 조회 =================
    @GetMapping("/orderList")
    public ModelAndView orderList(ModelAndView mv, HttpSession session) {

        try {
            // 회원정보 가져오기
            Member member = (Member) session.getAttribute("member");

            if (member == null) {
                Alert alert = new Alert("/member/login", "로그인이 필요한 서비스입니다.");
                mv.addObject("alert", alert);
                mv.setViewName("common/alert");
            }

            // 회원번호 기준으로 주문내역조회(orderDatail 목록)
            List<OrderDetail> orderList = mService.orderList(member);
            System.out.println("조회된 주문 개수: " + orderList.size()); // 뜨는지 확인..
            System.out.println("세션 memberNo: " + member.getMemberNo());

            mv.addObject("orderList", orderList);
            mv.setViewName("member/orderList");

        } catch (Exception e) {
            e.printStackTrace();
            mv.addObject("msg", e.getMessage()).setViewName("common/error");
        }
        return mv;
    }

}
