//页面加载完成之后  获取登录用户的信息--把用户名添加到页面中
$(function () {
	//debugger;
	//发送请求  处理响应数据
	$.get("/user.do?op=getLoginUser", function (result) {

		//处理响应数据
		console.log("showLoginUserName: ")
		console.log(result)
		// var getFlag = result.getFlag;
		var getFlag = result.loginFlag
		console.log("用户登录状态： " + getFlag)
		if (getFlag) {
			//用户已登录：把用户的username添加到页面中
			console.log('用户已登录')
			var username = result.loginUserName
			$("#usernameSpan").html("欢迎回来：" + username)
			//隐藏未登录状态
			$(".login_out").hide()

		} else {
			//未登录：隐藏登录状态
			$(".login").hide()
		}
	}, "json")

})