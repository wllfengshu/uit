# uit
> 修改idea因为包名过长导致单测报错(update idea test)
报错信息如下：
![eg](./img/eg.jpg)

## 项目架构

```lue
     |-app 业务逻辑
uit -|
     |             |-clickbutton 点击菜单执行
     |-plugin 插件 -|-importmodule 导入module时自动执行
                   |-importproject 导入project时自动执行

说明：app和plugin不应该有强耦合性，不得随意依赖
```

## 原理
修改项目的.idea/workspace.xml文件，在其中加入:
```xml
 <property name="dynamic.classpath" value="true" />
```

