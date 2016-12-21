# CoordinatorLayoutDemo
CoordinatorLayout控件的使用

##CoordinatorLayout与AppBarLayout

###注意点

发现TabLayout遮挡了ViewPager，ViewPager在Toolbar的下面

通过添加`app:layout_behavior="@string/appbar_scrolling_view_behavior"`修改

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

* CardView使用失效：需要在Cardview的标签中添加layout_margin，不然不能显示出来海拔效果，在CardView的外层使用layout_margin也不会显示

* 使用上面的修改，会在SDK低于21的手机上，出现浪费空间问题，参照[关于使用 CardView 开发过程中要注意的细节](http://www.open-open.com/lib/view/open1445759019585.html)进行修改

> 我们需要自定义一个 dimen 作为 CardView 的 Margin 值：

> 1.创建 /res/value 和 /res/value-v21 资源文件夹于项目对应 Module 目录下，前者放置旧版本/通用的资源文件（了解的可以跳过），后者放置 21 及更高 SDK 版本的资源文件。
> 
> 2.在 value 内的 dimen.xml 创建一个 Dimension （<dimen> 属性），随便命个名（如 xxx_card_margin）并填入数值 0dp。
> 
> 3.接着在 value-v21 文件夹内的 dimen.xml 创建名字相同的 Dimension，并填入你期望的预留边距（一般和 CardElevation 阴影大小相同）
> 
> 4.最后，在你布局中的 CardView 中设置android:layout_margin="@dimen/xxx_card_margin"
> 
> 这样依赖就解决了低版本中边距过大或者视觉效果不统一的问题了。

* CardView点击效果

> 如果你是用了 AppCompat v7 支持库：
> 
> 那么你可以直接给 CardView 加上 android:foreground="?attr/selectableItemBackground" 这个属性会在 Lollipop 上自动加上 > Ripple 效果，在旧版本则是一个变深/变亮的效果。

* 使用app:cardElevation增加CardView的海拔高度，需要引入命名空间

##参考

firstapp:

[CoordinatorLayout的使用如此简单](http://www.jianshu.com/p/72d45d1f7d55)

secondapp:

[DesignSupportLibraryExamples](https://github.com/PareshMayani/DesignSupportLibraryExamples)

[android CoordinatorLayout使用](http://blog.csdn.net/xyz_lmn/article/details/48055919)

[玩转AppBarLayout，更酷炫的顶部栏](http://www.jianshu.com/p/d159f0176576)