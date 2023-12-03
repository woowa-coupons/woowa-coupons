<div style="text-align: center;">
<img src = "https://github.com/woowa-coupons/woowa-coupons/assets/108214590/cdf34629-c60b-4a76-9d44-8d5e243bea5a" width="180px;">
</div>

# 🏍️ 배달의 민족 프로모션 클론 프로젝트
- 미션 기간: `23-10-09 ~ 23-11-03` [4 week]
- 2023 코드스쿼드 마스터즈 Max에서 진행한 자율 미션으로 배민 프로모션 시스템을 구현하는 프로젝트

<br/>

## 🧑🏻‍💻 팀원
|                                    백엔드                                    |                                    백엔드                                     |                                    백엔드                                     |                                    프론트엔드                                    |                                    프로트엔드                                    |
|:-------------------------------------------------------------------------:|:--------------------------------------------------------------------------:|:--------------------------------------------------------------------------:|:---------------------------------------------------------------------------:|:---------------------------------------------------------------------------:|
| <img src = "https://avatars.githubusercontent.com/23Yong" width="180px;"> | <img src = "https://avatars.githubusercontent.com/jinny-l" width="180px;"> | <img src = "https://avatars.githubusercontent.com/JJONSOO" width="180px;"> | <img src = "https://avatars.githubusercontent.com/qkdflrgs" width="180px;"> | <img src = "https://avatars.githubusercontent.com/crtEvent" width="180px;"> |
|                  [Bruni(브루니)](https://github.com/23Yong)                  |                  [Jinny(지니)](https://github.com/jinny-l)                   |                    [Jun(준)](https://github.com/JJONSOO)                    |                  [litae(리태)](https://github.com/qkdflrgs)                   |                   [Ape(에이프)](https://github.com/crtEvent)                   |

<br/>

## 🖥️ 동작 화면

### 앱
// 추가 예정

### 어드민
// 추가 예정

<br/>

## 🔖 목차
// 추가 예정

<br/>

## 🎯 프로젝트 목표

1. 동시성 문제 파악 및 개선
2. 성능 이슈 파악 및 개선

배민 프로모션 시스템 클론 프로젝트를 선택한 이유는 쿠폰 발급 시 발생할 수 있는 **동시성 문제를 해결하는 방법을 학습**하기 위함입니다.  
또한 DB에 대용량 데이터가 있는 상황에 많은 사용자가 동시에 쿠폰 발급을 요청했을 때의 **성능 이슈를 파악하고 개선하는 방법을 학습**하는 것이 목표입니다.

이에 따라 다음과 같은 과정으로 프로젝트를 진행했습니다.

- Step 0. 배민 프로모션 시스템 분석 및 기획
- Step 1. 동시성을 고려하지 않고 기능 구현
- Step 2. 동시성 문제 파악 및 해결
- Step 3. DB에 대용량 데이터 삽입 후 성능 이슈 파악 및 개선

<br/>

## 🔍 배민 프로모션 시스템 분석 및 기획
배민 앱을 바탕으로 요구사항을 1차적으로 정리했습니다.  
정리된 내용을 바탕으로 프론트는 figma 작성, 백엔드는 ERD와 API를 작성했습니다.

### 요구사항 분석 및 기획
- 상세 내용 확인 👉 [![Notion](https://img.shields.io/badge/Notion-%23000000.svg?style=for-the-flat&logo=notion&logoColor=white)](https://graceful-dracorex-565.notion.site/89927516548e424b99d66747c0f21475?pvs=4)

![image](https://github.com/woowa-coupons/woowa-coupons/assets/108214590/c16eca8c-3f1c-419d-b629-160e0f66481f)

### 피그마
- 어드민

![image](https://github.com/woowa-coupons/woowa-coupons/assets/108214590/cdaf121c-f452-4cc6-b17e-e616e500ae8f)

- 앱

![image](https://github.com/woowa-coupons/woowa-coupons/assets/108214590/0ec45a6f-d7e9-41c8-a8e6-2e506aa23a2b)

### 💾 ERD
![image](https://github.com/woowa-coupons/woowa-coupons/assets/108214590/d46f283d-b8b4-4397-9bdd-24d8caefd82d)

### 🌎 API 명세서
- 어드민 API: https://documenter.getpostman.com/view/28185148/2s9YJjReQ9
- 앱 API: https://documenter.getpostman.com/view/28185148/2s9YJjReQC

<br/>

## ⚙️ 인프라 구조
// 브루니가 추가할 예정

<br/>

## 🔧 기술 스택
- Java 17
- Spring Boot
- JPA
- AWS EC2, RDS, Amplify
- Jacoco
- RestDocs
- Github Actions
- Docker Compose

<br>

## ✏️ 프로젝트 기록
기능 구현을 하면서 발생한 문제점 해결 과정과 성능 개선 과정을 기록했습니다.

| No. | 제목                                                                                                                                                                                      | 작성자                                     |
|-----|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:----------------------------------------|
| 1   | [[Java] Java 17을 사용해야 하는 이유와 Java 17 변경점](https://velog.io/@jinny-l/Java-17)                                                                                                            | [Jinny(지니)](https://github.com/jinny-l) |
| 2   | [N+1 문제 해결하기!](https://velog.io/@bruni_23yong/JPA-N1-%EB%AC%B8%EC%A0%9C-%ED%95%B4%EA%B2%B0%ED%95%98%EA%B8%B0)                                                                           | [Bruni(브루니)](https://github.com/23Yong) |
| 3   | Equals, HashCode in Jpa Entity                                                                                                                                                          | [Jun(준)](https://github.com/JJONSOO)    |
| 4   | 작성중                                                                                                                                                                                     | [Jinny(지니)](https://github.com/jinny-l) |
| 5   | [쿠폰 발급 로직의 리팩토링 적용기](https://velog.io/@bruni_23yong/%EC%BF%A0%ED%8F%B0-%EB%B0%9C%EA%B8%89-%EB%A1%9C%EC%A7%81%EC%9D%98-%EB%A6%AC%ED%8C%A9%ED%86%A0%EB%A7%81-%EC%A0%81%EC%9A%A9%EA%B8%B0) | [Bruni(브루니)](https://github.com/23Yong) |
| 6   | [쿠폰 발급 동시성 제어하기, 성능테스트로 성능 개선하기]()                                                                                                                                                      | [Jun(준)](https://github.com/JJONSOO)    |
| 7   | [슬로우 쿼리 개선기](https://velog.io/@bruni_23yong/%EC%8A%AC%EB%A1%9C%EC%9A%B0-%EC%BF%BC%EB%A6%AC-%EA%B0%9C%EC%84%A0%EA%B8%B0)                                                                 | [Bruni(브루니)](https://github.com/23Yong) |
| 8   | 작성중                                                                                                                                                                                     | [Jinny(지니)](https://github.com/jinny-l) |

<br/>
