# CoordinatorLayoutDemo
CoordinatorLayout控件的使用

##CoordinatorLayout与AppBarLayout

###注意点

发现TabLayout遮挡了下面的ViewPager，ViewPager没有在TabLayout下方，而在Toolbar的下面

解决方式：在ViewPager内添加`app:layout_behavior="@string/appbar_scrolling_view_behavior"`得到解决

	<?xml version="1.0" encoding="utf-8"?>
	<android.support.v4.view.ViewPager
	    xmlns:android="http://schemas.android.com/apk/res/android"
	    xmlns:app="http://schemas.android.com/apk/res-auto"
	    xmlns:tools="http://schemas.android.com/tools"
	    android:id="@+id/viewpager"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent"
	    tools:context="com.example.fj.second.AppBarLayoutActivity"
	    app:layout_behavior="@string/appbar_scrolling_view_behavior"
	    tools:showIn="@layout/activity_app_bar_layout"/>
	
	    <!--"@string/appbar_scrolling_view_behavior"系统的-->
	
	    <!--不加app:layout_behavior="@string/appbar_scrolling_view_behavior"
	    会导致RecyclerView在Toolbar下面，被TabLayout遮盖-->

可以理解为在最外层CoordinatorLayout控件内，寻找带有`layout_behavior`属性的子控件，作为`CoordinatorLayout.Behavior`

没有找到`layout_behavior`属性的子控件，CoordinatorLayout就成为普通的FrameLayout了

##CardView

###步骤

1.添加`compile 'com.android.support:cardview-v7:24.2.1'`

2.布局中使用

	<?xml version="1.0" encoding="utf-8"?>
	<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
	                xmlns:app="http://schemas.android.com/apk/res-auto"
	                xmlns:tools="http://schemas.android.com/tools"
	                android:layout_width="wrap_content"
	                android:layout_height="wrap_content">
	    <!--xmlns:cardview="http://schemas.android.com/apk/res-auto"和xmlns:app="http://schemas.android.com/apk/res-auto"效果一样-->
	
	    <android.support.v7.widget.CardView
	        android:layout_width="match_parent"
	        android:layout_height="wrap_content"
	        android:layout_margin="8dp"
	        app:cardElevation="4dp">
	        <!--不加android:layout_margin，CardView全部紧凑在一起，看不到效果，而且必须加载CardView上，不能加在外层RelativeLayout-->
	        <!--cardview:cardElevation增加CardView的海拔高度，如果使用xmlns:app的命名空间要使用app:cardElevation-->
	
	        <RelativeLayout
	            android:layout_width="wrap_content"
	            android:layout_height="wrap_content">
	
	            <ImageView
	                android:id="@+id/item_list_iv_icon"
	                android:layout_width="wrap_content"
	                android:layout_height="wrap_content"
	                android:contentDescription="@string/desc"
	                tools:src="@drawable/icon_01"/>
	
	            <TextView
	                android:id="@+id/item_list_tv_name"
	                android:layout_width="wrap_content"
	                android:layout_height="wrap_content"
	                android:layout_centerVertical="true"
	                android:layout_marginLeft="10dp"
	                android:layout_marginStart="10dp"
	                android:layout_toEndOf="@id/item_list_iv_icon"
	                android:layout_toRightOf="@id/item_list_iv_icon"
	                android:textSize="20sp"
	                android:textStyle="bold"
	                tools:text="图片描述"/>
	
	        </RelativeLayout>
	
	    </android.support.v7.widget.CardView>
	
	</RelativeLayout>

###注意点

* CardView使用失效：需要在CardView的标签中添加`layout_margin`，不然不能显示出来海拔效果，在CardView的外层使用`layout_margin`也不会显示

![alt text](https://github.com/ffuujian/CoordinatorLayoutDemo/blob/master/art/cardview.png)

* 使用上面的修改，会在SDK低于21的手机上，出现浪费空间问题，参照[关于使用 CardView 开发过程中要注意的细节](http://www.open-open.com/lib/view/open1445759019585.html)进行修改

> 我们需要自定义一个 dimen 作为 CardView 的 Margin 值：

> 1.创建 `/res/value` 和 `/res/value-v21` 资源文件夹于项目对应 Module 目录下，前者放置旧版本/通用的资源文件（了解的可以跳过），后者放置 21 及更高 SDK 版本的资源文件。
> 
> 2.在 `value` 内的 `dimen.xml` 创建一个 `Dimension （<dimen> 属性）`，随便命个名（如 `xxx_card_margin`）并填入数值 0dp。
> 
> 3.接着在 value-v21 文件夹内的 dimen.xml 创建名字相同的 Dimension，并填入你期望的预留边距（一般和 `CardElevation` 阴影大小相同）
> 
> 4.最后，在你布局中的 CardView 中设置`android:layout_margin="@dimen/xxx_card_margin"`
> 
> 这样依赖就解决了低版本中边距过大或者视觉效果不统一的问题了。

* CardView点击效果

> 如果你是用了 AppCompat v7 支持库：
> 
> 那么你可以直接给 CardView 加上 `android:foreground="?attr/selectableItemBackground"` 这个属性会在 Lollipop 以上自动加Ripple（波纹）效果，在旧版本则是一个变深/变亮的效果。

* 使用`app:cardElevation`增加CardView的海拔高度，需要引入命名空间

##CollapsingToolbarLayout

###注意点

* Collapsing title：ToolBar的标题，当`CollapsingToolbarLayout`全屏没有折叠时，title显示的是大字体，在折叠的过程中，title不断变小到一定大小的效果。你可以调用`setTitle(CharSequence)`方法设置title

* Content scrim：ToolBar被折叠到顶部固定时候的背景，你可以调用`setContentScrim(Drawable)`方法改变背景或者 在属性中使用 `app:contentScrim="?attr/colorPrimary"`来改变背景

* Status bar scrim：状态栏的背景，调用方法`setStatusBarScrim(Drawable)`。还没研究明白，不过这个只能在Android5.0以上系统有效果

* Parallax scrolling children：CollapsingToolbarLayout滑动时，子视图的视觉差，可以通过属性`app:layout_collapseParallaxMultiplier="0.6"`改变

* CollapseMode ：子视图的折叠模式，有两种"pin"：固定模式，在折叠的时候最后固定在顶端；"parallax"：视差模式，在折叠的时候会有个视差折叠的效果，通常和`layout_collapseParallaxMultiplier`(设置视差因子)搭配使用

使用`app:layout_collapseMode="pin"`

![alt text](https://github.com/ffuujian/CoordinatorLayoutDemo/blob/master/art/collapseMode.png)

不使用`app:layout_collapseMode="pin"`

![alt text](https://github.com/ffuujian/CoordinatorLayoutDemo/blob/master/art/no_collapseMode.png)

* Toolbar的高度`layout_height`必须固定，不能`"wrap_content"`，否则Toolbar不会滑动，也没有折叠效果

* 为了能让FloatingActionButton也能折叠且消失出现，我们必须给FAB设置锚点属性`app:layout_anchor="@id/appbar"`意思是FAB浮动按钮显示在哪个布局区域
且设置当前锚点的位置`app:layout_anchorGravity="bottom|end|right"`意思FAB浮动按钮在这个布局区域的具体位置。两个属性共同作用才是的FAB 浮动按钮也能折叠消失，出现

* 给需要有折叠效果的组件设置`layout_collapseMode`属性

firstapp:

[关于CoordinatorLayout与Behavior的一点分析](http://www.jianshu.com/p/a506ee4afecb)

[CoordinatorLayout的使用如此简单](http://www.jianshu.com/p/72d45d1f7d55)

[Android 自定义控件之第二讲：TypedArray 详解](http://blog.csdn.net/zjh_1110120/article/details/50986589)

secondapp:

[DesignSupportLibraryExamples](https://github.com/PareshMayani/DesignSupportLibraryExamples)

[android CoordinatorLayout使用](http://blog.csdn.net/xyz_lmn/article/details/48055919)

[玩转AppBarLayout，更酷炫的顶部栏](http://www.jianshu.com/p/d159f0176576)

[Android M新控件之AppBarLayout，NavigationView，CoordinatorLayout，CollapsingToolbarLayout的使用](http://blog.csdn.net/feiduclear_up/article/details/46514791)