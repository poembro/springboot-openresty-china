# springboot 入门 demo
- 一个运行在java springboot上的服务端技术论坛项目 (处于学习开发中) 

---

## 特点
- 集成了 springboot + Spring Security (jwt授权认证) + redis + mybatis-plus + Knife4j 
- 多模块
- 完成目标 参考 openresty-china 

---

## 描述
- 目前该项目作为练手学习 springboot 开发, 持续更新中

## 环境 
- idea springboot2.5.14  mysql5.7  redis


## 接口文档


###  登录
```
> POST /admin/login HTTP/1.1
> Host: localhost:8081
> Content-Type: application/json
> User-Agent: insomnia/8.2.0
> Accept: */*
> Content-Length: 39

| {"username":"test","password":"123456"}
 
 
resp:
{
	"code": 200,
	"msg": "登录成功",
	"data": {
		"user": "test",
		"token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0IiwiYXV0aG9yaXRpZXMiOiJhZG1pbiwiLCJleHAiOjE3MDA3MzA1MDJ9.yVY3ALusmHPkXKwxoDoF86HWw9LkHlCHjy4snqYzTGVMyO7_KdIa-ewi8s8mUpfSWxh5tT9XXNmjr-4z42myaQ"
	}
}

```

### 带jwt-Token访问 topic/list
```
> GET /v1/topic/list?pageNum=3&pageSize=10&pageNum=1 HTTP/1.1
> Host: localhost:8081
> User-Agent: insomnia/8.0.0-beta.0
> Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0IiwiYXV0aG9yaXRpZXMiOiJhZG1pbiwiLCJleHAiOjE3MDA3MzA1MDJ9.yVY3ALusmHPkXKwxoDoF86HWw9LkHlCHjy4snqYzTGVMyO7_KdIa-ewi8s8mUpfSWxh5tT9XXNmjr-4z42myaQ
> Accept: */*
> Content-Length: 0
 
resp : 
{
	"code": 200,
	"msg": "请求成功",
	"data": {
		"totalPage": 51,
		"list": [
			{
				"id": 73,
				"title": "查看进程，按内存从大到小",
				"content": "```\n查看进程，按内存从大到小 \n[r"
             }
        ]
    }
}
```