# first come first served Form

### 프로젝트 소개

선착순 이벤트를 진행할 계획이 있으시다면 사용해보세요!

### 주제 선정 과정

주제 선정 당시 학교 내 단체들(학생회 등)에서 선착순 간식행사를 많이 진행했었는데 구글폼으로 직접 입력을 받아서 하는 작업들이 매우 불편해보였습니다. 응답을 입력 받아서 선착순 당첨자를 체크하고 안내를 해주려면 폼 전체 응답을 수기로 확인 후 제출 시간이 잘 맞는지, 당첨자수 세기, 직접 연락 돌리기 등 귀찮은 작업을 해야합니다.

이러한 문제를 개선하여 선착순 응답 폼에 특화된 페이지를 개발해보자하는 생각에서 출발했습니다.

### 중점사항
- 유저에게 보다 편리한 서비스를 제공하기 위한 고민을 바탕으로 프로젝트를 기획함
- 선착순 설문지 서비스를 구현하며 동시성 문제 및 대규모 시스템 설계에 대해서 고민함
- 웹 프론트를 직접 구현하여 fetch 요청을 통해 백엔드와의 통신을 경험함
- 개인 프로젝트지만 협업 프로젝트로 변환될 가능성도 고려하여 인프라 구축도 함께 진행함 (CI, CD, 배포)

### 개발기간

1차 개발 기간 : 2022-12-02 ~ 2023-01-06

2차 개발 기간 : 2023-02-07 ~ 2023-02-12

### 사용기술
<img src="https://img.shields.io/badge/Java 11-007396?style=flat-square&logo=Java&logoColor=white"/> <img src="https://img.shields.io/badge/Springboot 2.7.6-6DB33F?style=flat-square&logo=Springboot&logoColor=white"/> <img src="https://img.shields.io/badge/Gradle-02303A?style=flat&logo=Gradle&logoColor=white"/> <img src="https://img.shields.io/badge/MongoDB-47A248?style=flat&logo=MongoDB&logoColor=white"/> <img src="https://img.shields.io/badge/Redis-DC382D?style=flat&logo=Redis&logoColor=white"/>

<img src="https://img.shields.io/badge/HTML5-E34F26?style=flat-square&logo=HTML5&logoColor=white"> <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat-square&logo=JavaScript&logoColor=white"/> <img src="https://img.shields.io/badge/CSS-1572B6?style=flat-square&logo=CSS3&logoColor=white"/> <img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=flat-square&logo=Thymeleaf&logoColor=white"/>

<br>

### 프로젝트 구조 및 CI/CD
<img width="1920" alt="스크린샷 2023-02-08 오후 3 44 44(2)" src="https://user-images.githubusercontent.com/86194303/217454986-20e356cb-f12d-4581-b257-0b5adea11527.png">


### 고민한 이슈

[DB를 어떤 것으로 사용할까?](https://velog.io/@yeezze/DB%EB%A5%BC-%EC%96%B4%EB%96%A4-%EA%B2%83%EC%9C%BC%EB%A1%9C-%EC%82%AC%EC%9A%A9%ED%95%A0%EA%B9%8C)
<br>[다수의 유저들을 선착순으로 줄세워보자](https://velog.io/@yeezze/다수의-유저들을-선착순으로-줄세워보자)
<br>[Spring Scheduler 활용]()
<br>[리팩토링 (불변 객체, 캐시, 객체지향)](https://velog.io/@yeezze/객체지향적-관점으로-리팩토링을-해보자)
<br>[실시간 서버 통신 구현](https://velog.io/@yeezze/실시간-서버-통신-구현)
<br>[유저에게 보다 더 좋은 페이지를 제공하자 (SSE Event 리팩토링)](https://velog.io/@yeezze/SSE-event-리팩토링)

<br><br>
## 선착순 프로그램 기능 및 사용 방법

### 🔗 선착순 이벤트 생성

[https://first-form.shop/createForm.html](http://first-form.shop/createForm.html#)

1. 설문지 생성 페이지에 입장합니다.
<img width="1440" alt="IMG_6085" src="https://user-images.githubusercontent.com/86194303/224564388-709899e9-a628-46c8-930c-7f124eb8b706.PNG">

- 오른쪽 + 버튼을 누르면 설문지 항목을 추가할 수 있습니다.
- 질문 유형을 고를 수 있습니다. (현재 단답형, 객관식 질문 가능. 추후 추가 예정)
    - 객관식 질문 선택 시 옵션 추가 버튼을 누르면 답변 항목도 추가할 수 있습니다.
       <img width="1440" alt="IMG_6086" src="https://user-images.githubusercontent.com/86194303/224564429-c073662b-b10e-4a31-81bf-01942fd22998.PNG">

        

2. 원하는 내용대로 작성합니다.

<img width="1440" alt="IMG_6087" src="https://user-images.githubusercontent.com/86194303/224564470-b9481be3-2c8b-4dbb-9222-bd46da86e2b2.PNG">


3. 설정 페이지에서 설정값을 입력합니다.
    - 설정값을 필수로 입력해야합니다!
        <img width="1309" alt="IMG_6088" src="https://user-images.githubusercontent.com/86194303/224564519-5632a0b9-0acc-4c11-9c3e-9c74c390ed90.PNG">


4. 선착순 응답 시작 시간, 당첨자 수를 입력 후 보내기 버튼을 클릭합니다.
<img width="1434" alt="IMG_6089" src="https://user-images.githubusercontent.com/86194303/224564581-77c7276e-396c-4e7b-affc-375fa68967a8.PNG">



5. 보내기 버튼 클릭 시 참가 링크를 받을 수 있습니다. 
선착순 참가를 원하는 사람들에게 해당 링크를 보내어 홍보하시면 됩니다!<br>(복사 버튼 클릭 시 링크가 복사됩니다.)

<img width="1440" alt="IMG_6090" src="https://user-images.githubusercontent.com/86194303/224564631-363e9bc9-42a3-4f15-8f5d-ebc8b961764d.PNG">


<br>

### 🔗 선착순 이벤트 참여

1. 선착순 참가 링크로 입장합니다.

<img width="1440" alt="IMG_6091" src="https://user-images.githubusercontent.com/86194303/224564719-8e2db329-eb86-4b1c-8245-8fd8a319ea3c.PNG">

    

- 남은 시간 확인 버튼 클릭 시 이벤트 입장까지 남은 시간을 확인할 수 있습니다.
<img width="1440" alt="IMG_6092" src="https://user-images.githubusercontent.com/86194303/224564736-649244f3-dd8b-4ee1-a7b0-dbca3c3802f7.PNG">


    
- 입장 시간 이전에는 입장이 불가능합니다.
<img width="1440" alt="IMG_6093" src="https://user-images.githubusercontent.com/86194303/224564747-1b2161e0-bd29-4c06-81f3-84c792731843.PNG">


    

- 남은 시간 5초부터는 빨간 글씨로 표시되며 입장 시간에는 입장하세요! 라는 문구가 뜹니다.
<img width="1440" alt="IMG_6094" src="https://user-images.githubusercontent.com/86194303/224564753-86e3ea08-3039-4e9e-80f0-3028c6a185c8.PNG">



<img width="1440" alt="IMG_6095" src="https://user-images.githubusercontent.com/86194303/224564763-942b30bc-b793-44f7-ac16-7c6837107a49.PNG">


2. 입장 후에 폼 답변을 작성하고 제출 버튼을 클릭합니다.

<img width="1920" alt="IMG_6096" src="https://user-images.githubusercontent.com/86194303/224564779-2228eca7-1ad8-4420-9335-f92e09f16bbd.PNG">


<img width="1920" alt="IMG_6097" src="https://user-images.githubusercontent.com/86194303/224564790-f7f2825d-a9d0-49b0-829c-98fde6564fb7.PNG">

    

3. 제출 후에 실시간으로 변화되는 화면을 확인하며 나의 선착순 성공/실패 여부를 확인합니다.<br>(대기 번호 안내 또는 응답 완료 또는 선착순 마감 화면)
<img width="1440" alt="IMG_6098" src="https://user-images.githubusercontent.com/86194303/224564838-edafdb4a-7d51-4f9a-9174-1f33433196ea.PNG">
<img width="1440" alt="스크린샷 2023-01-06 오전 1 02 21" src="https://user-images.githubusercontent.com/86194303/217302300-babbcd97-9428-4d9a-950e-387d101d822a.png">
<img width="1440" alt="스크린샷 2023-01-06 오전 1 01 54" src="https://user-images.githubusercontent.com/86194303/217302317-c56fb84c-e9b1-40b3-8d3f-fb86578826aa.png">
