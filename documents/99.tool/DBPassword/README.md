# Durid 数据库密码加密

[参考](https://github.com/alibaba/druid/wiki/%E4%BD%BF%E7%94%A8ConfigFilter)

使用步骤：

1、生成密钥

copy download下来的durid的jar包，目录中为druid-1.1.10.jar也是目前系统使用的版本

命令行执行：

进入jar包所在目录输入一下命令，copy控制台信息保存

    java -cp druid-1.1.10.jar com.alibaba.druid.filter.config.ConfigTools #your_db_password#
    
控制台数据内容如下：
 
>privateKey：密钥

    privateKey:MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAi9h8ALKV22Dwb0INoUGvavivO6lBtVtWPNEYVR5NDJStxIPBcHfNCEd5Cq3GVqWZ+8RRyTV6tJJuRlYjQG54rQIDAQABAkEAix2phdwiHaGarWMtQeyg/64yTAx9NFePO2LabNp+1XKch509yTM5kTNMF81dNQfP4jpBspBCc
        Y5J4j9/5RmS9QIhAM2QGTXHdFvzsUO0k0h72nv0pAuTJoHNOdh8aAFY1CqfAiEAriiG8BRVYnaxBcKhmM9Nyy26jcqV25F0cXT/ng1fJTMCIGlO1vtsELpCn59V1NYfTRCPhbLAUcvRfT7gjM+T2C11AiB9gwmUdiL+/ikfzJ/nX+dhuUgMLecIQXCY38fkoSBjzwIhAIzUq067x+9olgvjklUv8Fp1ylveH
        2BIndN0cp46GIGv

>publicKey：公钥

    publicKey:MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIvYfACyldtg8G9CDaFBr2r4rzupQbVbVjzRGFUeTQyUrcSDwXB3zQhHeQqtxlalmfvEUck1erSSbkZWI0BueK0CAwEAAQ==

>password：密码

    password:JLXFlYO0ksWlcue/1IH7xLa9h3zON/n8a56CwQQ5M9GoEnHLd4/sR4Xwz1C/ng58vA9EvJEihV2xg/1KkRF1Vg==

2、工程配置

以下为application.yml配置

    publickey: MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIvYfACyldtg8G9CDaFBr2r4rzupQbVbVjzRGFUeTQyUrcSDwXB3zQhHeQqtxlalmfvEUck1erSSbkZWI0BueK0CAwEAAQ==
    spring:
      # DB 配置
      datasource:
        url: ******************
        username: ******************
        password: MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIvYfACyldtg8G9CDaFBr2r4rzupQbVbVjzRGFUeTQyUrcSDwXB3zQhHeQqtxlalmfvEUck1erSSbkZWI0BueK0CAwEAAQ==
        # druid 配置
        druid:
          filters: config
          connection-properties: config.decrypt=true;config.decrypt.key=${publickey}