<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>选择排列方式</title>
<!-- Bootstrap -->
<link href="../css/bootstrap.min.css" rel="stylesheet">
<script src="../css/jquery.min.js"></script>
<script src="../css/bootstrap.js"></script>
<style>

.myblack_overlay{ 
    display: none; 
    position: absolute; 
    top: 0%; 
    left: 0%; 
    width: 100%; 
    height: 100%; 
    background-color: black; 
    z-index:1001; 
    -moz-opacity: 0.6; 
    opacity:.60; 
    filter: alpha(opacity=66); 
} 
.mywhite_content { 
    display: none; 
    position: absolute; 
    top: 25%; 
    left: 25%; 
    width: 55%; 
    height: 55%; 
    padding: 20px; 
    border: 10px solid orange; 
    background-color: white; 
    text-align: center;
    z-index:1002; 
    overflow: auto; 
} 
</style>
</head>

<body>
	<div class="container">
		<h1>选择排列方式</h1>
		<div class="col-md-12">
			<!--JSTL判断并显示上传结果： -->
			<font size='4'>文件名：${fileName}</font>
			<c:set var="result" value="${result}"></c:set>
			<c:if test="${result=='true'}">
				<br><font size='4' color='green'>文件上传结果：上传成功</font>
			</c:if>
			<c:if test="${result=='false'}">
				<br><font size='4' color='red'>* 上传结果：上传失败！你上传的不是PPT文档，无法转换。请检查文件是否正确，然后重新上传。</font>
			</c:if>
		</div>
		
		<div class="col-md-6">
			<h3 style="color: #990066">普通用户模式</h3>
			<br>
			<h4>请选择排列方式：</h4>

			<form>
				<div class="radio">
					<div class="radio">
						<label> &nbsp;&nbsp;<input type="radio" name="chooser" 
							value="1*2" checked="checked" onclick= "javascript:preview0()"/>1*2
						</label>
					</div>
					<div class="radio">
						<label> &nbsp;&nbsp;<input type="radio" name="chooser"
							value="1*3" onclick= "javascript:preview1()"/>1*3
						</label>
					</div>
					<div class="radio">
						<label> &nbsp;&nbsp;<input type="radio" name="chooser"
							value="2*4" onclick= "javascript:preview2()"/>2*4
						</label>
					</div>
					<div class="radio">
						<label> &nbsp;&nbsp;<input type="radio" name="chooser"
							value="2*5" onclick= "javascript:preview3()"/>2*5
						</label>
					</div>
					<div class="radio">
						<label> &nbsp;&nbsp;<input type="radio" name="chooser"
							value="3*7" onclick= "javascript:preview4()"/>3*7
						</label>
					</div>
					<div class="radio">
						<label> &nbsp;&nbsp;<input type="radio" name="chooser"
							value="3*8" onclick= "javascript:preview5()"/>3*8
						</label>
					</div>
				</div>
			</form>

			<br>
			<h4>请选择你上传的PPT画幅比例：</h4>
			<form>
				<div class="radio">
					<label> &nbsp;&nbsp;<input type="radio" name="scale"
						value="1*2" onclick= "javascript:preview_0()" checked >4:3<br>
					</label>
				</div>
				<div class="radio">
					<label> &nbsp;&nbsp;<input type="radio" name="scale"
						value="1*3" onclick= "javascript:preview_1()" >16:9<br>
					</label>
				</div>
			</form>
			<br> <br> <input id="userBegin" type="button"
				class="btn btn-primary btn-lg btn-block" onclick="convertOnClick()"
				value="开始转换">
		</div>
		
		<style>
			img{border:solid 1px green;}
		</style>
		
		<!-- 效果预览图 -->
		<div class="col-md-6">
			<h3 style="color: #990066">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				效果预览（示例模板）
			</h3>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
			<img  src="../assets/images/default.png" id="picture" border="5" alt="图片加载失败" width="300" height="425"/>
			
			<br> <br> <br> <br> <br> <br>
		</div>

		<script>
		var rank=0;
		var scale=0;
		
		function preview0()
		{
			rank=0;
			preview();	
		}
		function preview1()
		{
			rank=1;
			preview();
		}
		function preview2()
		{
			rank=2;
			preview();
		}
		function preview3()
		{
			rank=3;
			preview();
		}
		function preview4()
		{
			rank=4;
			preview();
		}
		function preview5()
		{
			rank=5;
			preview();
		}
		
		function preview_0()
		{
			scale=0;
			preview();
		}
		function preview_1()
		{
			scale=1;
			preview();
		}
		
		function preview()
		{
			if(rank==0)
			{
				if(scale==0)
				{
					var pictureUrl = "../assets/images/4.3.1.2.png";
				}
				else
				{
					var pictureUrl = "../assets/images/16.9.1.2.png";
				}
			}
			else if(rank==1)
			{
				if(scale==0)
				{
					var pictureUrl = "../assets/images/4.3.1.3.png";
				}
				else
				{
					var pictureUrl = "../assets/images/16.9.1.3.png";
				}
			}
			else if(rank==2)
			{
				if(scale==0)
				{
					var pictureUrl = "../assets/images/4.3.2.4.png";
				}
				else
				{
					var pictureUrl = "../assets/images/16.9.2.4.png";
				}
			}
			else if(rank==3)
			{
				if(scale==0)
				{
					var pictureUrl = "../assets/images/4.3.2.5.png";
				}
				else
				{
					var pictureUrl = "../assets/images/16.9.2.5.png";
				}
			}
			else if(rank==4)
			{
				if(scale==0)
				{
					var pictureUrl = "../assets/images/4.3.3.7.png";
				}
				else
				{
					var pictureUrl = "../assets/images/16.9.3.7.png";
				}
			}
			else if(rank==5)
			{
				if(scale==0)
				{
					var pictureUrl = "../assets/images/4.3.3.8.png";
				}
				else
				{
					var pictureUrl = "../assets/images/16.9.3.8.png";
				}
			}
			document.getElementById("picture").src = pictureUrl;
		}
		</script>
		<div class="showdiv" id="showdiv"></div>
				

		<!-- 开发者模式begin -->
		<div class="col-md-6">
			<h3 style="color: #990066">* 开发者模式</h3>
			<br>
			<h4>你也可以输入自定义参数：</h4>
			<div class="col-md-3">
				<form>
					起点横坐标<input id="QI_HENG" class="form-control" name="zidingyi"
						type="text"><br> 起点竖坐标<input id="QI_SHU"
						class="form-control" name="zidingyi" type="text"><br>
					图的横长度<input id="TU_HENG" class="form-control" name="zidingyi"
						type="text"><br> 图的竖长度<input id="TU_SHU"
						class="form-control" name="zidingyi" type="text"><br>

				</form>
			</div>

			<div class="col-md-3">
				竖向减<input id="SHU_JIAN" class="form-control" name="zidingyi"
					type="text"><br> 竖向增<input id="HENG_ZENG"
					class="form-control" name="zidingyi" type="text"><br>
				横求余<input id="HENG_YU" class="form-control" name="zidingyi"
					type="text"><br> 竖求余<input id="SHU_YU"
					class="form-control" name="zidingyi" type="text"><br>

			</div>
			<br> <input type="button"
				class="btn btn-warning btn-lg btn-block" onclick="developer()"
				disabled="disabled" value="开始转换 (暂未开启，敬请期待)">
		</div>
		<!-- 开发者模式end -->

		<!-- 选择排列 比例 end -->

		<script>
		//处理选择的排列方式 比例
		function convertOnClick() 
		{
			<c:if test="${result=='false'}">
				alert("文件上传失败，无法转换！");
				return;
			</c:if>
			var myname= "${fileName}";
			var mychoose;
			var choosenum;
			var obj = document.getElementsByName("chooser");
		    for(var i=0; i<obj.length; i ++)
		    {
		        if(obj[i].checked)
		        {
		        	choosenum=i;
		        	mychoose = obj[i].value;
		            break;
		        }
		    }
			
		    var scale;
		    var obj1 = document.getElementsByName("scale");
		    if(obj1[0].checked)
	        {
		    	 scale = 0;
		    	// alert("文件名为：" + myname + "，你选择的排列方式为：" + mychoose+"，你选择的比例为4：3");
	        }
		    else if(obj1[1].checked)
	        {
		    	 scale = 1;
		    	// alert("文件名为：" + myname + "，你选择的排列方式为：" + mychoose+"，你选择的比例为16：9");
	        }
		    else
	    	{
		    	 scale = 0;
		    	// alert("文件名为：" + myname + "，你选择的排列方式为：" + mychoose+"，你没有选择比例，默认4：3");
	    	}
		    
			//如果上传成功 可以跳转至转换页面
			var showdiv=document.getElementById("showdiv");//加载中gif图片位置
			var url = "/WebPPTtoPDF1/ConvertServlet?myname=" + myname + "&choosenum=" + choosenum+ "&scale=" + scale;
			<c:if test="${result=='true'}">
				window.location.href = url;
				document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block';
			</c:if>
		}
	</script>
	
	<!-- 加载中遮罩 -->
	<div id="light" class="mywhite_content" >
		<font size="5">正在努力转换中 ~ 此过程大约需要20秒，请耐心等待<br><br></font>
		<img src='/WebPPTtoPDF1/image/loading.gif' border='0'  width='400px' height='300px'/>
	</div> 
    <div id="fade" class="myblack_overlay"></div> 

	</div>
</body>
</html>
