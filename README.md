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

* 使用CardView时需要在Cardview的标签中添加layout_margin，不然不能显示出来海拔效果，在CardView的外层使用layout_margin也不会显示

* 使用app:cardElevation增加CardView的海拔高度，需要引入命名空间

##参考

firstapp:

[CoordinatorLayout的使用如此简单](http://www.jianshu.com/p/72d45d1f7d55)

secondapp:

[DesignSupportLibraryExamples](https://github.com/PareshMayani/DesignSupportLibraryExamples)

[android CoordinatorLayout使用](http://blog.csdn.net/xyz_lmn/article/details/48055919)

[玩转AppBarLayout，更酷炫的顶部栏](http://www.jianshu.com/p/d159f0176576)