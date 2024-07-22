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



## 📚 기술적 의사결정
가상스레드   
추후 작성
## 🏹 주요 경험
### 1. Batch 성능 개선
#### 1.1 최종 성능
#### 1.2 성능개선 추이
- 성능개선추이 그래프 있음 좋고.
#### 1.3 주요 개선
- 1차 최적화: 구조 변경 시도 및 chunk 사이즈
- 2차 최적화: N + 1 쿼리 최적화
#### 1.4 [Spring Batch 성능 개선 기록](https://uttermost-band-f56.notion.site/Spring-Batch-89d7762014664bf9aae50d72676a143f?pvs=4)


## 트러블 슈팅

[프로젝트 전체 성능개선, 트러블 슈팅 모음 WIL](https://github.com/Bryan051/TIL/tree/main/OutcomeProject)

## 🔎 아키텍처

## 기능구현 요약


통계 및 정산 기능

부하 분산

CQRS 패턴 적용
