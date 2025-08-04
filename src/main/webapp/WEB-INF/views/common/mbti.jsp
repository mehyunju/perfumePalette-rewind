<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>향수 MBTI 테스트</title>
</head>
<body>
  <h1>Perfume MBTI Test</h1>

  <form action="/mbti/mbtiResult" method="post">

    <p>1. 내 마음에 쏙 든 향수를 발견했다! 계산하려는데 직원이 바쁜것 같다면?</p>
    <label><input type="radio" name="q1" value="E"> 저기요! 계산 먼저해주세요!</label><br>
    <label><input type="radio" name="q1" value="I"> 직원이 카운터로 올 때까지 딴청피우며 눈치본다.</label><br><br>

    <p>2. 가게 직원이 따라다니면서 이것저것 추천해준다면?</p>
    <label><input type="radio" name="q2" value="E"> 궁금했던 향수정보를 물어본다.</label><br>
    <label><input type="radio" name="q2" value="I"> 제발 말걸지 말아주세요.. 감사합니다..</label><br><br>

    <p>3. 향수가게를 나와 밥을 먹으려 한다. 어디로 갈까?</p>
    <label><input type="radio" name="q3" value="E"> 유행하는 핫플!</label><br>
    <label><input type="radio" name="q3" value="I"> 조용한 식사 장소</label><br><br>

    <p>4. 식당에 들어온 순간 가장 먼저 보이는 것은?</p>
    <label><input type="radio" name="q4" value="S"> 음식</label><br>
    <label><input type="radio" name="q4" value="N"> 인테리어</label><br><br>

    <p>5. 딱 하나의 향수만 시향 할 수 있다면?</p>
    <label><input type="radio" name="q5" value="S"> 좋아하던 향기</label><br>
    <label><input type="radio" name="q5" value="N"> 새로운 향기</label><br><br>

    <p>6. 향수 패키지가 너무 예쁘다. 어쩌지?</p>
    <label><input type="radio" name="q6" value="S"> 그냥 버린다</label><br>
    <label><input type="radio" name="q6" value="N"> 향수병 옆에 장식</label><br><br>

    <p>7. 직원 추천 향수 냄새가 너무 별로라면?</p>
    <label><input type="radio" name="q7" value="T"> 감사하지만 제 취향은 아님</label><br>
    <label><input type="radio" name="q7" value="F"> 좋은 것 같아요~ 다른 것도요</label><br><br>

    <p>8. 직원이 향수병을 다 깨뜨렸다면?</p>
    <label><input type="radio" name="q8" value="T"> '저게 다 얼마야..'</label><br>
    <label><input type="radio" name="q8" value="F"> '다치진 않으셨나..'</label><br><br>

    <p>9. 생일 선물 보냈는데 친구가 답이 없다면?</p>
    <label><input type="radio" name="q9" value="F"> 생일 아닌가? 잘못 보냈나?</label><br>
    <label><input type="radio" name="q9" value="T"> 바쁜가보네</label><br><br>

    <p>10. 향수를 사러 가기 전날 밤, 드는 생각은?</p>
    <label><input type="radio" name="q10" value="P"> 어떤 향수가 있을까?</label><br>
    <label><input type="radio" name="q10" value="J"> 오픈시간/교통 확인 완료!</label><br><br>

    <p>11. 향수가게가 아직 안 열었다!</p>
    <label><input type="radio" name="q11" value="P"> 다른 곳 구경하며 기다린다</label><br>
    <label><input type="radio" name="q11" value="J"> 오픈시간 다시 확인</label><br><br>

    <p>12. 친구 생일 선물로 향수를 사고 싶다. 언제 살까?</p>
    <label><input type="radio" name="q12" value="P"> 당일에 사면 되지!</label><br>
    <label><input type="radio" name="q12" value="J"> 일주일 전에 미리 준비</label><br><br>

    <button type="submit">제출하기</button>
  </form>
</body>
</html>
