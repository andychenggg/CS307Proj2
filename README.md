# CS307 Project 2

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
