
# CS307Proj2                        

## Basic Information                                           

### Names, student IDs, and the lab session

| **lab session** | **Names** | **student IDs** |
| --------------- | --------- | --------------- |
| 3               | 程嘉朗    | 12111622        |
| 3               | 伍福临    | 12110411        |



#### 



## API specification

1. `MainController.java`

   - `query`

     ```java
     @GetMapping("/query")
     @ResponseBody
     public List<Users> query()
     ```

     http请求类型: **get**

     响应数据格式: **json**

     功能：获取所有用户的列表，包含所有需要渲染的信息

