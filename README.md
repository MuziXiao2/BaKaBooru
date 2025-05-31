# BaKaBooru

BaKaBooru 是一款简单易用的个人艺术作品管理工具。

## 项目简介

BaKaBooru 是一个现代化的艺术作品管理系统，专注于为艺术爱好者、创作者提供一个简单而强大的作品管理平台。支持多语言界面，提供直观的用户体验。

## 技术架构

### 后端架构
- **框架**: Spring Boot 3.4.3
- **构建工具**: Maven
- **项目结构**: 多模块Maven项目

### 前端架构
- **框架**: Vue 3.5.13
- **构建工具**: Vite 6.0.11
- **UI框架**: Element Plus 2.9.10
- **状态管理**: Pinia 2.3.1
- **路由**: Vue Router 4.5.1
- **类型系统**: TypeScript
- **HTTP客户端**: Axios

## 主要功能

### 作品管理
- 作品上传与管理
- 支持多种文件格式
- 标签系统
- 作品分类管理
- 批量操作功能

### 用户界面
- 响应式设计
- 瀑布流展示
- 暗色/亮色主题

### 搜索功能
- 标签搜索
- 高级筛选
- 快速预览

## 开发指南

### 环境要求
- JDK 21+
- Node.js 18+
- Maven 3.8+

### 后端开发
```bash
cd backend
mvn spring-boot:run
```

### 前端开发
```bash
cd frontend
npm install
npm run dev
```

### 构建项目
```bash
# 构建整个项目
mvn clean install

# 单独构建前端
cd frontend
npm run build
```

## 部署说明

### 打包
```bash
mvn clean package
```

### 运行
```bash
java -jar backend/target/bakabooru-backend.jar
```

## 贡献指南
1. Fork 本仓库
2. 创建新的功能分支
3. 提交您的更改
4. 创建 Pull Request

## 许可证
本项目采用 MIT 许可证
