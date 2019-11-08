# 计算器 GUI

## 技术
- 表达式计算使用框架：`EvalEx`
  - 其支持自定义函数
  
### 表达式
- $lg(10) = log_{10} 10 = 1$
- $le(e) = log_e 10 = 1$
- $log(2, 8) = log_2 8 = 3$

#### 单位使用
- 需要使用 `*` 配合
- 2 千：`2k` 要写成 `2 * k`
- 2 万：`2w` 要写成 `2 * w`
- 2 百万：`2m` 要写成 `2 * m`
- 2 亿：`2y` 要写成 `2 * y`

### 创建镜像
- `gradle jlink`
- 位置在：`build\image\bin\$project`

### 经验
- 直接用 `JavaFX Scene Builder 2.0` 开发 fxml

#### 消息弹窗
- [原文参考](https://blog.csdn.net/qq_26954773/article/details/78215554)
```jshelllanguage
Alert alert = new Alert(Alert.AlertType.WARNING);
alert.setTitle("消息提示");
alert.setHeaderText("登录出错");
alert.setContentText("用户不存在或密码错误");
alert.showAndWait();
```

## 问题
- 自动获取焦点-放弃了