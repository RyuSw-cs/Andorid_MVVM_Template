# Android Template

### Outline

새로운 프로젝트 시작할 때 사용하는 안드로이드 템플릿

### Structure

- **Base Setting**
    - Gradle - 8.1
    - AGP - 8.1.4
    - JDK - 17
    - Kotlin - 1.9.22
- **Arhcitecture**
    - Clean Architecture
    - SAA (Single Activity Architecture)
    - MVVM (Model - View - ViewModel) with AAC
- **Skill**
    - DataBinding
    - Hilt
    - Coroutine
    - Flow
    - Retrofit2 + OkHttp
    - ListAdapter + DiffUtil
    - Navgation & Safe Args
    - Build Logic with VersionCatalog

### Implementation

```markdown
├── 📂app
│   ├── 📂Application
│   └── 📂Activity
├── 📂bulid-logic
│   └── 📂convention
├── 📂common-ui
│   ├── 📂binding-adapters
│   ├── 📂base
│   └── 📂view
├── 📂data
│   ├── 📂di
│   ├── 📂datastore
│   ├── 📂repository(implementation)
│   └── 📂remote
│       ├── 📂api
│       ├── 📂dto
│       ├── 📂interceptor
│       └── 📂mapper
├── 📂domain
│   ├── 📂entity
│   ├── 📂exception
│   ├── 📂repository(interface)
│   └── 📂usecase
└── 📂feature
    ├── 📂splash
    ├── 📂login
    ├── 📂movie-list
    └── 📂movie-detail
```
