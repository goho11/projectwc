<p align="center">
  <img src="https://github.com/user-attachments/assets/042778ff-b7ff-48e0-9442-fdee3f957e68">
</p>
<h1 align="center">
  이상형 월드컵 프로젝트
</h1>

## 이상형 월드컵이란?
- 월드컵과 같이 토너먼트 방식으로 자신이 선호하는 것을 선택하여 최종적으로 이상형을 찾는 게임
## 시연 영상
https://github.com/user-attachments/assets/bf41954b-0794-49dc-a8c0-cf4de64ad952
## 팀원 소개
- 이학석(팀장) [<img src="https://img.shields.io/badge/Git-이학석-red?logo=GITHUb">](https://github.com/HSLee1013)
- 이강호 [<img src="https://img.shields.io/badge/Git-이강호-green?logo=GITHUb">](https://github.com/LeeKangHo1) 
- 임영록 [<img src="https://img.shields.io/badge/Git-임영록-blue?logo=GITHUb">](https://github.com/Young14482) 
- 구예은 [<img src="https://img.shields.io/badge/Git-구예은-orange?logo=GITHUb">](https://github.com/goho11)
## 기술 스택
<img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white"><img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"><img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white"><img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=white"><img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css&logoColor=white"><img src="https://img.shields.io/badge/mustache-FF880F?style=for-the-badge&logo=mustache&logoColor=white"><img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white"><img src="https://img.shields.io/badge/postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white">

### IDE
<img src="https://img.shields.io/badge/intellijidea-000000?style=for-the-badge&logo=intellijidea&logoColor=white"><img src="https://img.shields.io/badge/Visual Studio Code-1E8CBE?style=for-the-badge&logo=Visual Studio Code&logoColor=white">

### 협업도구
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"><img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"><img src="https://img.shields.io/badge/slack-4A154B?style=for-the-badge&logo=slack&logoColor=white"><img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white"><img src="https://img.shields.io/badge/Discord-7289DA?style=for-the-badge&logo=discord&logoColor=white" alt="Discord"/>

### 데이터베이스
<img src="https://img.shields.io/badge/H2-FF4000?style=for-the-badge&logo=H2&logoColor=white"><img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL"/>
### 프로젝트 기간
- 2024년 12월 2일 ~ 2024년 12월 18일
## 목차
### 1. 프로젝트 설계([이동](#1-프로젝트-설계))
- 사이트맵
- ERD
- 초기 업무 분배
- 팀 노션 관리
### 2. 주요 기능([이동](#2-주요-기능))
- 유저 관련
- 월드컵 목록(메인 화면)
- 월드컵 만들기
- 월드컵 게임 플레이
- 월드컵 랭킹
### 3. 담당 업무([이동](#3-담당-업무))
---
## 1. 프로젝트 설계
- ### 사이트맵
![project wc sitemap](https://github.com/user-attachments/assets/93af0014-58a7-41a7-ad10-0bde83dbc40a)

- ### ERD
![ERD 최종](https://github.com/user-attachments/assets/4d2e82e9-ba59-4949-902e-a9d1f3b038b4)

- ### 초기 업무 분배
![최초 업무 분배](https://github.com/user-attachments/assets/4bf1e7fc-6d6a-44f7-93ea-7e6ecbb8334a)

- ### 팀 노션 관리
![팀 노션 문서 관리](https://github.com/user-attachments/assets/806f2e39-e107-4d31-a123-ff465c173ddf)

## 2. 주요 기능
### 유저 관련
- 로그인, 로그아웃
- 회원 가입 / 수정 / 탈퇴

![회원 정보 수정](https://github.com/user-attachments/assets/7a13e6c5-fd2d-4bfd-8f32-51b4103190ed)

### 월드컵 목록 
- 정렬 : 인기순, 최신순 
- 월드컵 제목으로 검색
![메인화면](https://github.com/user-attachments/assets/545aa253-e130-475d-9323-6297b974b4f4)

### 월드컵 만들기
- DropZone 이미지 업로드 : 드래그 앤 드롭으로 이미지 추가/변경
- 비동기 통신 : 월드컵 정보 및 이미지 수정
![월드컵 만들기](https://github.com/user-attachments/assets/2123b601-09e7-45b5-b517-fdb8fd0a8800)

### 월드컵 게임 플레이
- 두 이미지 중 선택된 이미지만 승리
- 클릭 시 승자와 패자 데이터를 숨겨진 폼에 설정
- 데이터를 서버에 제출하여 다음 라운드 진행
![월드컵 플레이](https://github.com/user-attachments/assets/c4a02b85-79dc-4948-a8b5-87b89e4d2d3a)

### 월드컵 랭킹
- 서버에서 이미지 목록 동적 로딩
- 정렬 : 이름, 우승비율, 승률
- 이미지 이름으로 검색
- 댓글 기능
![랭킹 화면](https://github.com/user-attachments/assets/ec218b12-5c88-4ca5-8467-a38e116f710a)

## 3. 담당 업무
# 여기서부턴 알아서 작성
- 이학석(팀장) 
	- 프로젝트 세팅 및 관리, 월드컵 만들기 구현, 테스트 및 오류 수정 
- 이강호 
	- 메인 화면(월드컵 목록 보기, 정렬, 검색), 댓글 관련 기능 구현 
- 임영록 
	- 게임 시작 전 설정 페이지 화면 및 기능 구현, 게임 플레이 화면 및 기능 구현 
- 구예은
	- 유저 기능 구현 
## 보완할 점
- 소셜 로그인
- 비로그인시 월드컵 게임/랭킹보기
- 게임 이어하기
- 신고하기
- 관리자 페이지
## 트러블 슈팅

## 느낀점
### 이학석
- 이번 프로젝트는 큰 그림을 그리고 체계적으로 설계하며 시작했지만, 일부 완성하지 못한 부분이 있어 아쉬움이 남는다.
- 비록 현재는 부족한 부분이 있지만, 혼자서라도 계획했던 모든 것을 구현하여 프로젝트를 완성할 것이다.
### 이강호
- response.body에서 데이터를 가져와야 하는데 response에서 가져 오는 바람에 2~3시간 날리는 등 어처구니 없는 실수에 시간을 많이 뺐긴 점이 아쉽습니다.
- 시큐리티 같은 프레임워크를 쓰려면 안의 로직이 어떻게 되는지 알아야 커스텀이 가능했기에 공부가 많이 되었습니다.
### 임영록
- 처음으로 체계적인 컨벤션과 규칙을 정해 프로젝트를 진행하다 보니 초반에는 다소 불편했지만, 덕분에 팀원들과 협업 능력을 키우며 매우 유익한 경험을 할 수 있었다.
### 구예은
- 담당한 부분에 기능을 추가 구현하지 못해 아쉬웠지만, 기본 구현 이해도를 높일 수 있었다.
