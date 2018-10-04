# tankwar @ dear teammates
90版坦克大战,`↑  ↓ ← →`移动，空格发射子弹。
> 参考游戏：http://www.4399.com/flash/4963.htm

# 先闻其声
![](snapshot/1.png)
![](snapshot/2.png)

# 待做
+ 敌方AI坦克
+ 游戏欢迎界面
+ 做一个地图编辑器，添加更多游戏场景
+ 资源释放

# 小教程~
> libgdx官方文档参见**https://github.com/libgdx/libgdx/wiki**,中文文档可以参见**https://blog.csdn.net/xietansheng/article/details/50185655**

配置[lib](./lib)中的四个依赖jar，然后使用[启动器](src/desktop/DesktopLauncher.java)即可开始游戏~

游戏逻辑是首先[启动器](src/desktop/DesktopLauncher.java)负责启动游戏，加载[欢迎界面](src/core/game/MenuScreen.java)。当用户在欢迎界面点击开始游戏后跳转到
[游戏主界面](src/core/game/GameScreen.java),主界面初始化三个[stage(类似于舞台)](src/core/game/GameScreen.java#L23):
+ `mapComponentStage` 存放地图物体，如boss，砖块，铁块(这些物体即[MapEntity](src/core/entity/MapEntity.java))
+ `bulletStage` 存放玩家坦克发射的子弹物体([BulletEntity](src/core/entity/BulletEntity.java))
+ `tankStage` 存放坦克([TankEntity](src/core/entity/TankEntity.java))
这些`Entity`都有两个重要方法，可以根据需要重写:
```java
    @Override
    public void draw(Batch batch, float parentAlpha) {
        //渲染绘图逻辑，比如绘制坦克图形
    }

    @Override
    public void act(float delta) {
        //游戏逻辑，比如坦克移动，发射子弹这些纯粹的不需要绘图的逻辑
    }
```