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

## 🏹 주요 경험
### 1. Batch 성능 개선
#### 1.1 최종 성능
- 3천5백만건 기준: 6분
#### 1.2 성능개선 추이
|단계|데이터|처리시간|개선율|
:---|:---|:---|:---
|기존|1천만|13분| -
|1차 성능 개선|1천만|1분|93%
|2차 성능 개선|3천5백만|10분|-
|3차 성능 개선|3천5백만|6분|40%
|3-2차 개선|3천5백만|실패|-
|4차 성능 개선|3천만|1분40초|86%
| '' |6천만(3천만*2)|3분40초|
#### 1.3 주요 개선
- 1차 최적화: 구조 변경 시도 및 chunk 사이즈
- 2차 최적화: N + 1 쿼리 최적화, userId, videoId, created_at 을 기준으로 데이터 인덱싱.
- 3차 최적화: Task Executor 멀티스레드 적용, 병렬 실행
- 4차 최적화: Partitioniing 적용
#### 1.4 [📈 Spring Batch 성능 개선 기록](https://uttermost-band-f56.notion.site/Spring-Batch-89d7762014664bf9aae50d72676a143f?pvs=4)

### 2. 구조 최적화 및 동시성 제어
#### 2.1 플랫폼 스레드 활용
- 고성능 처리를 위한 최신 Java 플랫폼 스레드 기술 적용
#### 2.2 Chunk 동시성 제어
- 데이터 처리의 효율성 증대를 위한 Chunk 기반 동시성 관리
  - 파티셔닝으로 중복 데이터 방지
### 3. 부하 분산
#### 3.1 CQRS
- CQRS 패턴 적용
  - MAIN(쓰기)과 REPLICA(읽기) 책임의 명확한 분리
  - read, write Repository 분리
- DB Main-Replica 구조

|구분|역할|특징|
:---|:---|:---
|Main DB|쓰기 작업 전담|데이터 일관성 보장|
|Replica DB|읽기 작업 전담|조회 성능 최적화|

- 데이터 동기화: ROW 단위 실시간 동기화로 정합성 유지
- 트래픽 분산: Replica DB를 통한 읽기 작업 부하 분산 및 가용성 향상

## 🚨 트러블 슈팅
### 1. 멀티스레드 성능개선과 문제점
  - PoolSize 와 QueueCapacity 최적화
  - Rejection 예외, Shuntdown 시 task 유실 방지
    - [📖 ThreadPoolExecutor 고찰](https://uttermost-band-f56.notion.site/d0f326a7ccb348d1a3cd63867afb32ba?pvs=4)
    - [📖 Hikari CP 데드락](https://uttermost-band-f56.notion.site/Hikari-CP-9085c6e5349d4eaf8946c751e1dc8ae7?pvs=4)
### 2. DB 데이터
- Buffer pool and Memory, Connection Pool 등 데이터 삽입과 트랜잭션 등에 대한 트러블 관리.   
[📖 프로젝트 전체 성능개선, 트러블 슈팅 모음 WIL](https://github.com/Bryan051/TIL/tree/main/OutcomeProject)

## 📚 기술적 의사결정
### 파티셔닝과 멀티스레딩
- Multi-threaded 는 동시적 접근 즉 concurrency 개념이라면 Partitioning 는 병렬접근 즉 parallel 개념으로, 배치프로그램 성능개선을 위한 다양한 시도.
[📖 파티셔닝과 멀티스레딩](https://uttermost-band-f56.notion.site/a64e711d9bb943aba7da3538e972c511?pvs=4)

## 🔎 아키텍처

## 📃 프로젝트 상세
### ⚙️️ 주요 기술 스택
### 📘 API 문서
### 📙ERD
