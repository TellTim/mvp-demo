# mvp-demo
MVP demp
目的
通过剖析MVP Demo中的关键要素，来深入了解如何使用MVP框架
 
分包结构说明
1.	demo。
	一般是项目中围绕某个业务功能的一个模块，建议单独放在一个包中，以便后续考虑是否可以从项目中分离出去，独立成一个脱离项目的模块
2.	model(角色M)
	一般由bean和model组成，
	bean是简单的POJO对象;
	model是P用来从数据库或者网络中获取数据的，demo中用线程池来执行M对应的操作(分DiskIoPool,NetworkPool和MainThread).
3.	presenter(角色P)
   	持有M和V的实例，通过M来拿到数据，交给V去渲染页面(实现M的回调方法，在回调方法中拿到数据交给V去渲染页面)
4.	view(角色V)
	在Activity或者Fragment实现V中的方法，V不包含业务逻辑，只负责将数据去渲染页面.
	
MVP中接口方法的约束(建议供参考)
1.	M中的方法的命名，可包含关键字insert，delete，update，query，方法通过回调方法来返回数据，回调方法的在P中具体实现，具体操作交给线程池来处理:
2.	P中的方法命名，可包含关键字save，remove，update，get/load，实现M中的回调方法，在回调方法中将M返回的数据交给V，P通过getView()来获取V中定义好的接口方法
3.	V中负得方法命名，可包含关键字remove，update，show/refresh，V只负责将数据渲染页面，避免Activity或Fragment臃肿

