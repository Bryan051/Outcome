# 정산 프로젝트
🧑‍💻 2024.06.19 ~ 진행중   
Batch Link: [Outcome Batch](https://github.com/Bryan051/OutcomeBatch)
## 기술스택
<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=white"> <img src="https://img.shields.io/badge/MYSQL-4479A1?style=for-the-badge&logo=MYSQL&logoColor=white"> <img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=Docker&logoColor=white"> <img src="https://img.shields.io/badge/Github Actions-2088FF?style=for-the-badge&logo=Github Actions&logoColor=white"> <img src="https://img.shields.io/badge/AWS EC2-FF9900?style=for-the-badge&logo=AWS EC2&logoColor=white">
## 🌱 프로젝트 소개
- 대량의 영상 시청기록에 대한 통계 정산 Batch
- 부하분산을 위한 CQRS 패턴 및 장애 복구, 가용성을 위한 HA DB, 서버분리.
## 🛠️ 주요 기능
1. 통계 및 정산
  - Chunk Oriented Processing으로 배치 작업 수행

2. 부하분산 및 장애복구
  - 배치 서버 분리
  - master/slave 구조로 가용성 DB 구축
  - CQRS

## 아키텍처

## 📚 기술적 의사결정
가상스레드   
추후 작성
## 성능개선
<details><summary>Batch 구조 개선, 트러블 슈팅</summary>
  1. reader(paging), processor, writer(chunk) 구조에서, video 를 읽어오고 processor 에서 videoview 를 조회해 count 하는 방식이다.</br>
  2. paging 으로 많은 데이터를 끊어서 가져오는게 reader 의 의의라면, 수만건이 쌓이는 videoview 를 대상으로 읽는게 나은 방법이 아닐까?</br>

  - N+1 문제가 발생.
  - processor 에서 join fetch를 한다면?
  - update 를 해야되는, 누적합을 고려할 테이블이 있으면 reader로 paging 해서 들고 와 계산 한 값에 또 계산하는 경우가 발생할 가능성이 있다.
    <details><summary>리팩토링</summary>
      1. 데이터 양과 서버의 메모리 용량에 따라 페이지 사이즈를 조절 해 Video 객체와 관련된 VideoView 목록을 모두 가져와 필터링하고 통계, 정산. (X)</br>
      2. 단일 책임 원칙을 유지한채 (reader 에서 video만) processor 에서 COUNT와 SUM으로 단일 결과를 반환하도록 한다.</br></br>
      - 배치(통계) 시간: ideo_view 3천만개/ 13분</br>   <img width="496" alt="image" src="https://github.com/user-attachments/assets/f34e8073-106b-49fa-82eb-1b9a6be9e02b"></br>
  배치 리팩토링:[Batch Refactoring](https://github.com/Bryan051/TIL/blob/main/OutcomeProject/Batch.md), N + 1 개선: [N+1](https://github.com/Bryan051/TIL/blob/main/OutcomeProject/Batch_N%2B1.md)
    </details>
</details>    

## 트러블 슈팅

[프로젝트 전체 성능개선, 트러블 슈팅 모음](https://github.com/Bryan051/TIL/tree/main/OutcomeProject)
## 기능구현 요약


통계 및 정산 기능

부하 분산

CQRS 패턴 적용
