# Entities 

- User 
- Role 
- Account
- UserAccount 
- UserAccountID

# Controllers

- UserController (User and UserAccount management)
  - Talk with UserService
  
- AccountController (Account management)
  - Talk with AccountService
  
- RoleController (Role management)
  - Talk with RoleService

# Services

- UserService (User and UserAccount management)
  - Talk with userRepository, AccountRepository, RoleRepository and userAccountRepository
  
- AccountService (Account management)
  - Talk with AccountRepository and userAccountRepository
  
- RoleService (Role management)
  - Talk with RoleRepository

# Repositories

- UserRepository
- AccountRepository
- RoleRepository
- UserAccountRepository
- UserAccountIDRepository

