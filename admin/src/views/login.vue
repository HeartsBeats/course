<template>
  <div class="main-container">
    <div class="main-content">
      <div class="row">
        <div class="col-sm-10 col-sm-offset-1">
          <div class="login-container">
            <div class="login-box visible widget-box no-border">
              <div class="widget-body">
                <div class="widget-main" align="center">
                  <img class="login_imag" src="@/assets/logo.png" auto-complete="on">
                  <div class="space-6"></div>
                  <form class="loginForm" auto-complete="on" label-position="left">

                    <h4 class="title">
                      <i class="ace-icon fa fa-leaf green"></i>
                      <span class=""> &nbsp;前端控台登录</span>
                    </h4>

                    <fieldset class="fieldset">
                      <label class="block ">
                          <span class="block input-icon input-icon-right">
                            <input v-model="user.loginName" type="text" class="form-control" placeholder="用户名"/>
                            <i class="ace-icon fa fa-user"></i>
                          </span>
                      </label>

                      <label class="block ">
                          <span class="block input-icon input-icon-right">
                            <input v-model="user.password" type="password" class="form-control" placeholder="密码"/>
                            <i class="ace-icon fa fa-lock"></i>
                          </span>
                      </label>

                      <label class="block ">
                          <span class="block input-icon input-icon-right">
                            <div class="input-group">
                              <input v-model="user.imageCode" type="text" class="form-control" placeholder="验证码">
                              <span class="input-group-addon" id="basic-addon2">
                                <img v-on:click="loadImageCode()" id="image-code" alt="验证码"/>
                              </span>
                            </div>
                          </span>
                      </label>

                      <div class="space"></div>

                      <div class="clearfix">
                        <label class="inline" style="float: left" >
                          <input v-model="remember" type="checkbox" class="ace"/>
                          <span class="lbl">记住我</span>
                        </label>

                        <div align="center">
                          <button type="button"
                                  class="width-50 push_away btn btn-sm btn-primary"
                                  v-on:click="login()"
                                  >
                              <i class="ace-icon fa fa-key"></i>
                              <span class="bigger-110">登录</span>
                          </button>
                        </div>
                      </div>
                    </fieldset>
                  </form>
                </div><!-- /.widget-main -->
              </div><!-- /.widget-body -->
            </div><!-- /.login-box -->
          </div>
        </div><!-- /.col -->
      </div><!-- /.row -->
    </div><!-- /.main-content -->
  </div><!-- /.main-container -->
</template>

<script>
export default {
  name: "login",
  data: function () {
    return {
      user: {},
      remember: true, // 默认勾选记住我
      imageCodeToken: "",
    }
  },
  mounted: function () {
    let _this = this;
    $("body").removeClass("no-skin");
    $("body").attr("class", "login-layout light-login");
    // 从缓存中获取记住的用户名密码，如果获取不到，说明上一次没有勾选“记住我”
    let rememberUser = LocalStorage.get(LOCAL_KEY_REMEMBER_USER);
    if (rememberUser) {
      _this.user = rememberUser;
    }
    // 初始时加载一次验证码图片
    _this.loadImageCode();
  },
  methods: {
    login() {
      let _this = this;
      // 将明文存储到缓存中
      // let passwordShow = _this.user.password;
      // 如果密码是从缓存带出来的，则不需要重新加密
      let md5 = hex_md5(_this.user.password);
      let rememberUser = LocalStorage.get(LOCAL_KEY_REMEMBER_USER) || {};
      if (md5 !== rememberUser.md5) {
        _this.user.password = hex_md5(_this.user.password + KEY);
      }
      _this.user.imageCodeToken = _this.imageCodeToken;
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/user/login', _this.user).then((response) => {
        Loading.hide();
        let resp = response.data;
        if (resp.success) {
          console.log("登录成功：", resp.content);
          let loginUser = resp.content;
          Tool.setLoginUser(resp.content);
          // 判断“记住我”
          if (_this.remember) {
            // 如果勾选记住我，则将用户名密码保存到本地缓存
            // 原：这里需要保存密码明文，否则登录时又会再加一层密
            // 新：这里保存密码密文，并保存密文md5，用于检测密码是否被重新输入过
            let md5 = hex_md5(_this.user.password);
            LocalStorage.set(LOCAL_KEY_REMEMBER_USER, {
              loginName: loginUser.loginName,
              // password: _this.user.passwordShow,
              password: _this.user.password,
              md5: md5
            });
          } else {
            // 没有勾选“记住我”时，要把本地缓存清空，否则按照mounted的逻辑，下次打开时会自动显示用户名密码
            LocalStorage.set(LOCAL_KEY_REMEMBER_USER, null);
          }
          _this.$router.push("/welcome");
          // window.open("/welcome", "_self")
        } else {
          Toast.warning(resp.message);
          _this.user.password = "";
          _this.loadImageCode();
        }
      });
    },

    /**
     * 加载图形验证码
     */
    loadImageCode: function () {
      let _this = this;
      _this.imageCodeToken = Tool.uuid(8);
      $('#image-code').attr('src', process.env.VUE_APP_SERVER + '/system/admin/kaptcha/image-code/' + _this.imageCodeToken);
    },
  }
}
</script>

<style scoped>
.input-group-addon {
  padding: 0;
}

.login_imag {
  width: 100px;
  height: 100px;
  display: flex;
  display: -webkit-flex;
  align-items: center;
  justify-content: center;
}


.login-container {
  margin-top: 100px;
  min-height: 100%;
  overflow: hidden;
  background: linear-gradient(to bottom, #00F5A0, #00D9F5);
}

.button {

}
.loginForm {
  position: center;
  width: 600px;
  max-width: 100%;
  padding: 90px 10px 0;
  align-content: center;
  overflow: hidden;
  background: #F7F7F7;
}

.block {
  margin: 20px auto;
}

.btn {
  flex-flow: row;
}
.fieldset {
  margin-top: 20px;
}

.title {
  margin-top: 0px;
  padding-top: 0px;
}

.row {
  background: linear-gradient(to bottom, #00F5A0, #00D9F5);
}

</style>
