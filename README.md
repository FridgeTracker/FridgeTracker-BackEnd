# Backend Disabled as of 13/04/2024

## API Endpoints

### Users

- POST /api/register: Create a new user.
- POST /api/login: Login using email and password for User responds with User object.
- GET /api/user/{uuid}: Get user by user id. Returns [User](#User) Object
- POST /api/updateUser: Update user details. Returns [Message](#String)
  z
### Fridges

- POST /api/addFridge: Create a new fridge. (requires fridge data and user email)

### Members

- POST /api/addMember: Add member to family account
- GET  /api/member/{id}: using member UUID

### Items

- POST /api/addItem: Add item to fridge
- POST /api/updateItem: update details of a item
- POST /api/deleteItem: Delete item from fridge


### Meal Plans

- POST /api/add_meal: Add the information of a single meal.
- GET  /api/meal_plans: Get all informatin of all meals.
- POST  /api/load_meal_plans: Load meals from json file.

### Meal Records

- POST /api/mealRecords: Add the information of a single meal record.
- GET  /api/mealRecords: Get all informatin of all meal records.


## Table of Contents

- [Fridge](#Fridge)
- [User](#User)
- [Member](#Member)
- [Item](#Item)
- [Meal_Plan](#Meal_Plan)
- [Ingredients](#Ingredients)
- [NutritionalInformation](#NutritionalInformation)
- [Meal_Record](#Meal_Record)

  
### User
| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
|  id  | [Long](#int64) | optional |  |
|  name  | [string](#string) | optional |  |
|  email  | [string](#string) | optional |  |
|  password  | [string](#string) | optional |  |
|  rank  | [string](#string) | optional |  |
|  fridges  | [Fridge](#Fridge) | repeated |  1..* Relationship |
|  members  | [Member](#Fridge) | repeated |  1..* Relationship|


### Fridge
| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
|  id  | [Long](#int64) | optional |  |
|  storageName  | [string](#string) | optional |  |
|  capacity  | [int32](#int32) | optional |  |
|  items  | [Item](#Item) | repeated |  1..* Relationship |


### Member
| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
|  id  | [Long](#int64) | optional |  |
|  name  | [string](#string) | optional |  |
|  age  | [int32](#int32) | optional |  |
|  allergies  | [string](#string) | optional |  |
|  height  | [Long](#int64) | optional |  |
|  weight  | [Long](#int64) | optional |  |
|  imageURL  | [string](#string) | optional |  |


### Item
| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
|  itemID  | [Long](#int64) | optional |  |
|  foodName  | [string](#string) | optional |  |
|  quantity  | [int32](#int32) | optional |  |
|  calories  | [Long](#int64) | optional |  |
|  type  |  [string](#string)  |  optional  |  |


### Meal_Plan
| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
|  mealID  | [Long](#int64) | Primary Key |  |
|  mealName  | [string](#string) | Optional |  |
|  mealType  | [string](#string) | Optional |  |
|  mealImage  | [string](#string) | Optional | URL of the image path |
|  description  |  [string](#string)  | Optional |  |


### Ingredients
| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
|  mealPlanID  | [Long](#int64) | Foreign Key | References Meal_Plan(id) |
|  ingredients  | [string](#string) | Optional |  |


### NutritionalInformation
| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
|  mealPlanID  | [Long](#int64) | Primary Key | References Meal_Plan(id), Composite PK |
|  key  | [Long](#int64) | Primary Key | Composite PK |
|  value  | [string](#string) | Optional |  |


### Meal_Record
| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
|  mealRecordID  | [Long](#int64) | Primary Key |  |
|  mealPlanID  | [Long](#int64) | Optional |  |
|  memberID  | [Long](#int64) | Optional |  |
|  consumedDate  | [Date](#date) | Optional |  |



## Scalar Value Types

| .proto Type | Notes | C++ | Java | Python | Go | C# | PHP | Ruby |
| ----------- | ----- | --- | ---- | ------ | -- | -- | --- | ---- |
| <a name="double" /> double |  | double | double | float | float64 | double | float | Float |
| <a name="float" /> float |  | float | float | float | float32 | float | float | Float |
| <a name="int32" /> int32 | Uses variable-length encoding. | int32 | int | int | int32 | int | integer | Bignum or Fixnum (as required) |
| <a name="int64" /> Long | Uses variable-length encoding. | int64 | long | int/long | int64 | long | integer/string | Bignum |
| <a name="bool" /> bool |  | bool | boolean | boolean | bool | bool | boolean | TrueClass/FalseClass |
| <a name="string" /> string | A string must always contain UTF-8 encoded or 7-bit ASCII text. | string | String | str/unicode | string | string | string | String (UTF-8) |
| <a name="date" /> Date | Represents calendar dates. | std::chrono::system_clock::time_point | java.util.Date | boolean | datetime.date | time.Time | System.DateTime |  |


