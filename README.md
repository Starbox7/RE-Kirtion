![header](https://capsule-render.vercel.app/api?type=wave&color=auto&height=300&section=header&text=Notion%20Clone%20Project&fontSize=80)

<h2 align="center">Notion 스타일 메모 및 튜토리얼 제공 프로젝트</h2>

<p align="center">
  다양한 메모 기능과 새로운 회원을 위한 튜토리얼 및 소개 페이지를 제공하는 서비스입니다.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/status-active-brightgreen" alt="Project Status">
  <img src="https://img.shields.io/badge/license-MIT-blue" alt="License">
  <img src="https://img.shields.io/github/languages/top/yourusername/notion-clone" alt="Top Language">
</p>

## 📋 목차

- [💡 프로젝트 소개](#-프로젝트-소개)
- [✨ 주요 기능](#-주요-기능)
- [💻 기술 스택](#-기술-스택)
- [⚙️ 설치 및 실행 방법](#️-설치-및-실행-방법)
- [📱 사용법](#-사용법)
- [🤝 기여 방법](#-기여-방법)
- [📄 라이선스](#-라이선스)

## 💡 프로젝트 소개

**Notion Clone Project**는 사용자가 `/` 키를 통해 다양한 메모 기능에 쉽게 접근할 수 있도록 하며, 새로운 사용자를 위한 튜토리얼 및 소개 페이지를 제공하는 서비스입니다. 이 프로젝트는 사용자 경험을 중시하며, 단순하면서도 강력한 메모와 기록 기능을 제공하여 협업과 기록을 한층 더 원활하게 합니다.

## ✨ 주요 기능

- **다양한 메모 기능**: `/` 키를 통해 텍스트, 체크박스, 이미지, 코드 블록 등 다양한 메모 옵션을 빠르게 삽입할 수 있습니다.
- **실시간 편집**: 작성 중인 메모를 실시간으로 자동 저장하여 데이터 손실을 방지합니다.
- **튜토리얼 및 소개 페이지**: 첫 회원 가입 시 튜토리얼과 기능 소개 페이지를 자동으로 제공하여 사용자가 쉽게 서비스에 익숙해질 수 있습니다.
- **반응형 UI**: 모바일, 태블릿, 데스크탑에서 모두 최적화된 화면을 제공하여 사용성을 높입니다.

## 💻 기술 스택

- **Frontend**: React, Mui component 
- **Backend**: Spring-boot 
- **Database**: Postgresql
- **Real-time Collaboration**: react-query
- **Authentication**: Email Authentication 
- **CI/CD**: GitHub 

### 클론 및 의존성 설치

1. 이 저장소를 클론합니다.
```
   git clone https://github.com/yourusername/notion-clone.git
   cd notion-clone
```
3. 백엔드와 프론트엔드 의존성을 각각 설치합니다.

```
# 백엔드 설치
cd backend
npm install

# 프론트엔드 설치
cd ../frontend
npm install
```

### 2. 환경 변수 설정

- `.env` 파일을 생성하고 필요한 환경 변수를 설정합니다.

```
   # 백엔드 환경 변수
   MONGO_URI=your_mongodb_uri
   PORT=5000
   JWT_SECRET=your_jwt_secret

   # 프론트엔드 환경 변수
   REACT_APP_API_URL=http://localhost:5000
```

### 3. 서버 및 클라이언트 실행

1. 백엔드 서버를 실행합니다.

```
   cd backend
   npm start
```
2. 프론트엔드 클라이언트를 실행합니다.
```
cd ../frontend
npm start
```

---

## 📱 사용법

1. **회원 가입**: 회원 가입 후 처음 로그인하면, 서비스 튜토리얼과 주요 기능을 안내하는 소개 페이지가 표시됩니다.
2. **메모 작성**: `/` 키를 눌러 텍스트, 체크박스, 이미지 등을 추가할 수 있는 메뉴를 호출하여 다양한 포맷으로 메모를 작성합니다.
4. **실시간 편집**: 다른 사용자와 함께 메모를 편집하면 실시간으로 반영됩니다.
5. **메모 검색**: 키워드를 입력해 필요한 메모를 빠르게 찾을 수 있습니다.

## 🤝 기여 방법

1. 이슈를 확인하고 기여할 작업을 선택합니다.
2. 기능 추가나 버그 수정을 위해 브랜치를 생성합니다.

 ```
   git checkout -b feature/새기능
```

---

## 📄 라이선스

이 프로젝트는 MIT 라이선스 하에 배포됩니다.

<p align="center">
  Made with ❤️ by the Notion Clone Project Team
</p>
