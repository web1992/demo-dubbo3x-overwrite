# 工程简介

dubbo 3.x `dubbo.reference.*`,`dubbo.service.*` 属性服覆盖导致配置不生效问题复现。

- demo-dubbo3x-overwrite

1. 启动 com.gbdmf.demo.dubbo3.EmbeddedZooKeeper
2. 启动 com.gbdmf.demo.dubbo3.xml.provider.DubboXmlProviderApplication
3. 启动 com.gbdmf.demo.dubbo3.xml.consumer.DubboXmlConsumerApplication

启动项目发下consumer配置的超时时间没有生效！

在 [dubbo-consumer.xml](src%2Fmain%2Fresources%2Fspring%2Fdubbo-consumer.xml) 中配置了接口`com.gbdmf.demo.dubbo3.api.GreetingsService`
的超时时间`6000`ms，但是如果我在 [DubboXmlConsumerApplication.java](src%2Fmain%2Fjava%2Fcom%2Fgbdmf%2Fdemo%2Fdubbo3%2Fxml%2Fconsumer%2FDubboXmlConsumerApplication.java) 文件中设置了
`System.setProperty("dubbo.reference.timeout", "3000")` 系统变量，那么在 xml 中设置的超时时间就会被覆盖了。导致超时时间设置不生效。

此问题是在dubbo 3.x 中才出现的问题，这个会导致项目中的接口配置的超时时间都会失效。而且问题很难排查。

此外我用了 nacos 这样的配置中心，如果在 nacos 的配置中心，配置了公共的配置，比如`dubbo.reference.timeout=3000` 这个命名规范刚好满足了dubbo 属性的覆盖规则。那么就影响了所有的服务！