##简介
平台使用微服务分布式系统架构，将选课、在线学习、成绩统计等功能细化到离散的服务中，减少系统间的耦合性，系统架构如图所示。分离的微服务减少各层之间的依赖，可以独立部署，服务之间可以通过标准化的轻量级互联网协议（如HTTP）或者消息传递协议（如RabbitMQ）通信，每个微服务都包含各自的用户处理功能、业务逻辑和后端功能，微服务架构极大地提升了系统的扩展性，  降低了系统的维护成本。微服务具有一定的容错性，松耦合的架构决定某一组件的故障不一定会导致整个系统的瘫痪，其正常运行的服务还可以满足用户的请求。
API网关层是微服务结构中的入口，可以提供身份验证、授权、请求转换、缓存等功能。服务应用层是整个架构的核心，包含一组独立、自治的微服务。每个微服务负责特定的业务功能，可以独立开发、部署和扩展，微服务之间通过轻量级的通信机制进行交互。基础服务层包括服务注册与发现，负载均衡、故障恢复、监控和日志等基础设施组件，这些组件支持微服务的运行和管理。存储技术层中使用存储数据库mysql和缓存数据库redis，每个微服务都有独立的数据存储，主要负责存储和管理微服务所需要的数据。支持服务层提供了一些基础设施和支持服务，帮助微服务的开发、部署和运行，包括服务注册与发现、配置管理、负载均衡、故障恢复等。运行资源层提供了运行微服务的服务器和网络基础设施，包括交换机和路由器。CI/CD层提供了自动化构建、测试、部署和交付流程，以支持微服务的快速迭代。

 ![image](https://github.com/jihuodexiaoyang/NuistLaborEdu/assets/116045163/b2b0f02f-63e1-46ec-897e-0f051ab930e1)

 

##角色和用户的设计
劳动教育系统为满足学校、教师对学生的劳动教育管理，以及学生使用劳动教育系统的需求，设定分层登录用户，分别是系统管理员、学校管理员、学院管理员、教师、学生五种角色。考虑到用户的角色定位不同，需要给用户授权自己职责范围内的功能，角色功能如图3所示。其中系统管理员的权限最高，可以对登录的用户进行操作，并且授权一定的数据权限给教师和学生。
##模块功能的设计
系统包括三大模块，课程管理模块、成绩管理模块、系统管理模块，课程管理模块如图4所示。课程管理模块包括学习功能、选课功能、已选课程管理功能等，成绩管理模块包括成绩统计功能、成绩考核功能、课程预警功能等，系统管理模块主要分为用户和角色管理功能、部门和岗位管理功能、登录日志管理功能、系统监控和系统工具、通知公告、校园地图以及数据分析。

 ![image](https://github.com/jihuodexiaoyang/NuistLaborEdu/assets/116045163/77d586d8-5d3c-4d98-bd9a-2ed686c11d2e)


 ![image](https://github.com/jihuodexiaoyang/NuistLaborEdu/assets/116045163/c4279634-dccf-44fb-b09f-d17118320857)



##前端界面
![image](https://github.com/jihuodexiaoyang/NuistLaborEdu/assets/116045163/8e74dd7e-61ad-42f1-a1a3-3874952322a2)
![image](https://github.com/jihuodexiaoyang/NuistLaborEdu/assets/116045163/bf3112f7-2aa2-4d2e-8dcd-b62506005a39)
![image](https://github.com/jihuodexiaoyang/NuistLaborEdu/assets/116045163/9ef01bb3-50c9-46c1-a33e-9b7781469f93)
![image](https://github.com/jihuodexiaoyang/NuistLaborEdu/assets/116045163/a3ad4cde-c5f8-4ebb-9068-ada562af556f)
![image](https://github.com/jihuodexiaoyang/NuistLaborEdu/assets/116045163/bcf20067-f8a3-48db-9d7c-8dc4ba83ba71)
![image](https://github.com/jihuodexiaoyang/NuistLaborEdu/assets/116045163/3d962f10-d06a-4fe4-bf2f-dcf037005158)
![image](https://github.com/jihuodexiaoyang/NuistLaborEdu/assets/116045163/b6c3983e-d3cb-4809-8979-82c49745106a)

