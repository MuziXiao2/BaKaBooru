# **BaKaBooru**

🎨 **BaKaBooru** 是一款现代化、前后端分离的个人图库，支持对象存储、标签管理、图片搜索与多文件管理，旨在帮助创作者更高效地组织和浏览自己的作品。

---

## 项目简介

**BaKaBooru** 由个人独立设计与开发，面向个人创作者与图像管理需求，提供：

- 简洁直观的用户界面（UI）
- 高效的作品增删改查能力
- 灵活的标签系统和搜索功能
- 文件去重与对象存储优化

本项目支持良好的前后端解耦开发体验，使用现代技术栈构建，开发过程中结合 AI 工具辅助，提升效率与可维护性。

>  **项目正在开发中，以下功能计划后续完善：**
>
> - 自动生成标签（基于图像内容分析）
> - 多用户账号与权限管理
> - 图片分享与公开访问机制

---

## 技术架构

### 后端架构

- **核心框架**：Spring Boot `3.4.3`
- **ORM**：Spring Data JPA
- **数据库**：MySQL 8+
- **文件存储**：MinIO（基于 Hash 的图片去重）
- **构建工具**：Maven（多模块结构）
- **API 调试**：Apifox

### 前端架构

- **核心框架**：Vue `3.5.13`（Composition API + TypeScript）
- **UI 框架**：Element Plus `2.9.10`，Naive UI（局部组件）
- **状态管理**：Pinia `2.3.1`
- **路由管理**：Vue Router `4.5.1`
- **构建工具**：Vite `6.0.11`
- **HTTP 客户端**：Axios
- **辅助开发**：GitHub Copilot、Cursor、Grok、ChatGPT

---

## 核心功能

### 图片与作品管理

- 支持上传、预览、删除多个文件
- 一张作品可关联多个图片文件
- 自动基于文件 Hash 进行图片去重（确保存储唯一）

### 标签与搜索系统

- 支持为图片打多个标签
- 标签管理功能（新增、修改、删除）
- 组合条件搜索（标题 / 标签 / 排序）
- 支持未来扩展分组 / 层级标签

### 用户界面体验

- 响应式设计，兼容多终端
- 瀑布流风格展示作品
- 作品详情弹窗支持快速编辑
- 支持暗色 / 亮色模式切换

---

### 环境要求

- JDK 21+
- Node.js 18+
- Maven 3.8+

---

## 部署教程

### 1. 准备工作

1. 下载 MinIO 可执行文件
   - 访问 [MinIO Github仓库](https://github.com/minio/minio)
   - 根据您的操作系统下载对应版本
   - 将文件放置在项目根目录或其他指定目录

2. 克隆项目
```bash
git clone https://github.com/yourusername/BaKaBooru.git
cd BaKaBooru
```

### 2. 后端配置

1. 创建配置文件 `backend/src/main/resources/application.properties`：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/bakabooru?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai
    username: your_username
    password: your_password

minio:
  # MinIO可执行文件路径（根据实际位置修改）
  exec-path: minio.exe  # Windows
  
  # MinIO数据存储目录（可自定义）
  data-path: data/minio
  
  # MinIO访问凭证（建议修改默认值）
  root-user: minioadmin
  root-password: minioadmin
  
  # MinIO存储桶名称（可自定义）
  bucket-name: bakabooru
```

2. 编译并运行后端
```bash
# 进入后端目录
cd backend

# 清理并构建项目
mvn clean install

# 如果是第一次运行，需要先编译整个项目
cd ..
mvn clean install -DskipTests

# 运行后端应用（以下两种方式选择一种）
# 方式1：直接运行（开发环境推荐）
cd backend
mvn spring-boot:run

# 方式2：构建jar包后运行（生产环境推荐）
cd backend
mvn clean package
java -jar target/bakabooru-0.0.1-SNAPSHOT.jar
```
### 3. 前端配置

1. 安装依赖
```bash
cd frontend
npm install
```

2. 开发环境运行
```bash
npm run dev
```

3. 生产环境构建
```bash
npm run build
```

### 4. 访问应用

`http://localhost:5173`（开发环境）或 `http://localhost:80`（生产环境）

---

## ⚙️ MinIO 配置说明

### 配置项说明

| 配置项 | 说明 | 默认值 | 示例 |
|-------|------|--------|------|
| exec-path | MinIO可执行文件路径 | minio.exe | ./minio 或 D:/minio.exe |
| data-path | 数据存储目录 | data/minio | /data/minio 或 D:/minio-data |
| root-user | 管理员用户名 | minioadmin | admin |
| root-password | 管理员密码 | minioadmin | your-secure-password |
| bucket-name | 存储桶名称 | bakabooru | images |