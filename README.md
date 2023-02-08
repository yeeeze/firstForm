# first come first served Form

### 프로젝트 소개

선착순 이벤트를 진행할 계획이 있으시다면 사용해보세요!

### 주제 선정 과정

주제 선정 당시 학교 내 단체들(학생회 등)에서 선착순 간식행사를 많이 진행했었는데 구글폼으로 직접 입력을 받아서 하는게 너무 불편해보였습니다. 
입력 받아서 선착순 당첨자를 체크하고 안내를 해주려면 폼 전체 응답을 수기로 확인 후 제출 시간이 잘 맞는지, 당첨자수 세기, 직접 연락 돌리기 등 귀찮은 작업을 해야합니다. 

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

![스크린샷 2023-02-07 오후 5.04.21.png](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/e0fbb919-618a-478b-ac55-48f9975eda97/스크린샷_2023-02-07_오후_5.04.21.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230207%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230207T161624Z&X-Amz-Expires=86400&X-Amz-Signature=0a3ba12ae27c95f4e3b6ce6f2b83d524c7baf177f6d3782477a63813f7639aa0&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22%25EC%258A%25A4%25ED%2581%25AC%25EB%25A6%25B0%25EC%2583%25B7%25202023-02-07%2520%25EC%2598%25A4%25ED%259B%2584%25205.04.21.png%22&x-id=GetObject)

- 오른쪽 + 버튼을 누르면 설문지 항목을 추가할 수 있습니다.
- 질문 유형을 고를 수 있습니다. (현재 단답형, 객관식 질문 가능. 추후 추가 예정)
    - 객관식 질문 선택 시 옵션 추가 버튼을 누르면 답변 항목도 추가할 수 있습니다.
        
        ![스크린샷 2023-02-07 오후 5.06.06.png](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/0ae1794b-e57d-4eac-ae31-0d6f0f800142/스크린샷_2023-02-07_오후_5.06.06.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230207%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230207T161650Z&X-Amz-Expires=86400&X-Amz-Signature=8489fccfed0a301b840f0b9536c3840c8eff670031b5c3a124d01066192adb25&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22%25EC%258A%25A4%25ED%2581%25AC%25EB%25A6%25B0%25EC%2583%25B7%25202023-02-07%2520%25EC%2598%25A4%25ED%259B%2584%25205.06.06.png%22&x-id=GetObject)
        

2. 원하는 내용대로 작성합니다.

![스크린샷 2023-02-07 오후 5.07.17.png](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/4039c756-a5c2-464e-99ae-1e8c051c1b3e/스크린샷_2023-02-07_오후_5.07.17.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230207%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230207T161711Z&X-Amz-Expires=86400&X-Amz-Signature=304ae0bdcef3ceb1bd4c987a9e0a3b251b1399b1a4196fe44b96e27b01696471&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22%25EC%258A%25A4%25ED%2581%25AC%25EB%25A6%25B0%25EC%2583%25B7%25202023-02-07%2520%25EC%2598%25A4%25ED%259B%2584%25205.07.17.png%22&x-id=GetObject)

3. 설정 페이지에서 설정값을 입력합니다.
    - 설정값을 필수로 입력해야합니다!
        
![스크린샷 2023-02-07 오후 5.11.58.png](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/110339a2-28da-49b5-9538-ea53852a3543/스크린샷_2023-02-07_오후_5.11.58.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230207%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230207T161725Z&X-Amz-Expires=86400&X-Amz-Signature=ce5cc4d85463b945290f5494370baeaa0cc34c8873b4c988eb4fc285e3c0e68e&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22%25EC%258A%25A4%25ED%2581%25AC%25EB%25A6%25B0%25EC%2583%25B7%25202023-02-07%2520%25EC%2598%25A4%25ED%259B%2584%25205.11.58.png%22&x-id=GetObject)
        
4. 선착순 응답 시작 시간, 당첨자 수를 입력 후 보내기 버튼을 클릭합니다.

![스크린샷 2023-02-07 오후 5.14.20.png](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/59a45c7f-3933-47c1-8a93-044488ff9bde/스크린샷_2023-02-07_오후_5.14.20.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230207%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230207T161802Z&X-Amz-Expires=86400&X-Amz-Signature=149ae99b6ab0d4635935d724d7abace06fcc52166eab40ff44cade8a0dab7aad&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22%25EC%258A%25A4%25ED%2581%25AC%25EB%25A6%25B0%25EC%2583%25B7%25202023-02-07%2520%25EC%2598%25A4%25ED%259B%2584%25205.14.20.png%22&x-id=GetObject)

5. 보내기 버튼 클릭 시 참가 링크를 받을 수 있습니다. 
선착순 참가를 원하는 사람들에게 해당 링크를 보내어 홍보하시면 됩니다!<br>(복사 버튼 클릭 시 링크가 복사됩니다.)

![스크린샷 2023-02-07 오후 5.01.39.png](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/d4e573c8-5848-4cee-a7cf-1903a112333d/스크린샷_2023-02-07_오후_5.01.39.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230207%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230207T161817Z&X-Amz-Expires=86400&X-Amz-Signature=d7c61095c656cff6b7f2c8fe306e157f77206b578f2b6b0fb70d32c64cdb7b13&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22%25EC%258A%25A4%25ED%2581%25AC%25EB%25A6%25B0%25EC%2583%25B7%25202023-02-07%2520%25EC%2598%25A4%25ED%259B%2584%25205.01.39.png%22&x-id=GetObject)

<br>

### 🔗 선착순 이벤트 참여

1. 선착순 참가 링크로 입장합니다.

![스크린샷 2023-02-07 오후 5.18.57.png](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/51be17cf-d84f-41cc-9920-523b7d9ce871/스크린샷_2023-02-07_오후_5.18.57.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230207%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230207T161833Z&X-Amz-Expires=86400&X-Amz-Signature=d4c30d5e79d8acb2fc669152314e9a990120351ef01bca602e009330246f5331&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22%25EC%258A%25A4%25ED%2581%25AC%25EB%25A6%25B0%25EC%2583%25B7%25202023-02-07%2520%25EC%2598%25A4%25ED%259B%2584%25205.18.57.png%22&x-id=GetObject)
    

- 남은 시간 확인 버튼 클릭 시 이벤트 입장까지 남은 시간을 확인할 수 있습니다.

![스크린샷 2023-02-07 오후 5.22.31.png](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/ab1957ed-c840-4680-ae1a-f5019bb8b78e/스크린샷_2023-02-07_오후_5.22.31.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230207%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230207T161856Z&X-Amz-Expires=86400&X-Amz-Signature=ec97be4735e2c3c6167d0baded982943b62d7194e3bd9c573adc4de20e2ebc4d&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22%25EC%258A%25A4%25ED%2581%25AC%25EB%25A6%25B0%25EC%2583%25B7%25202023-02-07%2520%25EC%2598%25A4%25ED%259B%2584%25205.22.31.png%22&x-id=GetObject)
    
- 입장 시간 이전에는 입장이 불가능합니다.

![스크린샷 2023-02-07 오후 5.22.50.png](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/9ee32991-36ba-4fae-9632-34dd8d83d6fa/스크린샷_2023-02-07_오후_5.22.50.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230207%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230207T161909Z&X-Amz-Expires=86400&X-Amz-Signature=c7a7f291d71ae9414e32718abce60a68beeed97e142abad568580d1f2be83d38&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22%25EC%258A%25A4%25ED%2581%25AC%25EB%25A6%25B0%25EC%2583%25B7%25202023-02-07%2520%25EC%2598%25A4%25ED%259B%2584%25205.22.50.png%22&x-id=GetObject)
    

- 남은 시간 5초부터는 빨간 글씨로 표시되며 입장 시간에는 입장하세요! 라는 문구가 뜹니다.

![스크린샷 2022-12-31 오전 12.46.56.png](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/83b1e3f2-2351-4370-9412-7408ea4e5ecd/스크린샷_2022-12-31_오전_12.46.56.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230207%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230207T161928Z&X-Amz-Expires=86400&X-Amz-Signature=4b229ff1cfd2bf6412113ee651676cf3a1c8d365ffb5afc6fe6840594fadb8e8&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22%25EC%258A%25A4%25ED%2581%25AC%25EB%25A6%25B0%25EC%2583%25B7%25202022-12-31%2520%25EC%2598%25A4%25EC%25A0%2584%252012.46.56.png%22&x-id=GetObject)

![스크린샷 2022-12-31 오전 12.47.03.png](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/2816097d-a0c0-4b10-be72-49d20a971e1f/스크린샷_2022-12-31_오전_12.47.03.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230207%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230207T161948Z&X-Amz-Expires=86400&X-Amz-Signature=c2175213162f81bc5d1c8a8cfe653195cb82cbe6d70f6b50152116e5f22dc9b7&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22%25EC%258A%25A4%25ED%2581%25AC%25EB%25A6%25B0%25EC%2583%25B7%25202022-12-31%2520%25EC%2598%25A4%25EC%25A0%2584%252012.47.03.png%22&x-id=GetObject)

2. 입장 후에 폼 답변을 작성하고 제출 버튼을 클릭합니다.

![스크린샷 2023-02-07 오후 5.24.43(2).png](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/57063560-7ae2-4192-bfc5-35bdd1cfd950/스크린샷_2023-02-07_오후_5.24.43%282%29.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230207%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230207T162002Z&X-Amz-Expires=86400&X-Amz-Signature=b87c2e194896661b82a05d06667d73f93a0f4d74e7f0a89100242c2efa73ea5e&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22%25EC%258A%25A4%25ED%2581%25AC%25EB%25A6%25B0%25EC%2583%25B7%25202023-02-07%2520%25EC%2598%25A4%25ED%259B%2584%25205.24.43%282%29.png%22&x-id=GetObject)
    
![스크린샷 2023-02-07 오후 5.25.06(2).png](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/85aac4c9-e1c7-45a1-804d-2db6918be5a1/스크린샷_2023-02-07_오후_5.25.06%282%29.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230207%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230207T162021Z&X-Amz-Expires=86400&X-Amz-Signature=221fd82efdb03c77cf26c2b882c3c9ebae575c03fa82d63858aaa41b485923b5&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22%25EC%258A%25A4%25ED%2581%25AC%25EB%25A6%25B0%25EC%2583%25B7%25202023-02-07%2520%25EC%2598%25A4%25ED%259B%2584%25205.25.06%282%29.png%22&x-id=GetObject)
    

3. 제출 후에 실시간으로 변화되는 화면을 확인하며 나의 선착순 성공/실패 여부를 확인합니다.<br>(대기 번호 안내 또는 응답 완료 또는 선착순 마감 화면)

![스크린샷 2023-01-06 오전 1.01.01.png](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/e961b91f-68b7-4dce-86cf-e093fb2a2353/스크린샷_2023-01-06_오전_1.01.01.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230207%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230207T162036Z&X-Amz-Expires=86400&X-Amz-Signature=5c390a997fca1b0a69941ff2eb487b99ba0170a1114e8bdf8e618b3d814821b0&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22%25EC%258A%25A4%25ED%2581%25AC%25EB%25A6%25B0%25EC%2583%25B7%25202023-01-06%2520%25EC%2598%25A4%25EC%25A0%2584%25201.01.01.png%22&x-id=GetObject)
    
<img width="1440" alt="스크린샷 2023-01-06 오전 1 02 21" src="https://user-images.githubusercontent.com/86194303/217302300-babbcd97-9428-4d9a-950e-387d101d822a.png">
<img width="1440" alt="스크린샷 2023-01-06 오전 1 01 54" src="https://user-images.githubusercontent.com/86194303/217302317-c56fb84c-e9b1-40b3-8d3f-fb86578826aa.png">
