# IAGORA-WINGMAN

MVVM - Clean architecture - KOIN Dependency injection - Modularization

- Struktur Modul 
    -  DI           : Component module for injection in KOIN
    - DATA         : Data Source from repository to interface API
    - DOMAIN       : Data Model, interface to implement in DATA Module
    - PRESENTATION : View Acticity, Fragment
    
- Modul
  - app 
    - Auth : Login , Register
    - Chat
    - Main Menu
  - Core     : Core module 
  - BuildSrc : All Config version of library in build gradle/ dependency
  - Gallery  : Camera Module using Camerax
  - Market   : Add Product, List Product, Add Market
  - Process order
  - Receive Order
