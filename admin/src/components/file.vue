<template>
    <div>
      <button type="button" v-on:click="selectFile()" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-upload"></i>
        {{text}}
      </button>
      <input class="hidden" type="file" ref="file" v-on:change="uploadFile()" v-bind:id="inputId+'-input'">
    </div>
</template>

<script>

/*
着当你使用 file 中的模板时，camelCase (驼峰命名法) 的 prop
名需要使用其等价的 kebab-case (短横线分隔命名) 命名：
props: {
title: String,
    likes: Number,
    isPublished: Boolean,
    commentIds: Array,
    author: Object,
    callback: Function,
    contactsPromise: Promise // or any other constructor
}
 prop 可以通过 v-bind 动态赋值
 */
export default {
  name: 'file',
  props: {
    text: {
      default: "上传文件"
    },
    inputId: {
      default: "file-upload"
    },
    suffixs: {
      default: []
    },
    afterUpload: {
      type: Function,
      default: null
    },
    use : {
      default: ""
    },
  },
  data: function () {
    return {
    }
  },
  methods: {
    uploadFile () {
      let _this = this;
      let formData = new window.FormData();
      let file = _this.$refs.file.files[0];

      // 判断文件格式
      let suffixs = _this.suffixs;
      let fileName = file.name;
      let use = _this.use;
      let suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length).toLowerCase();
      let validateSuffix = false;
      for (let i = 0; i < suffixs.length; i++) {
        if (suffixs[i].toLowerCase() === suffix) {
          validateSuffix = true;
          break;
        }
      }
      if (!validateSuffix) {
        Toast.warning("文件格式不正确！只支持上传：" + suffixs.join(","));
        $("#" + _this.inputId + "-input").val("");
        return;
      }

      // key："file"必须和后端controller参数名一致
      formData.append('file', file);
      formData.append('use', use);
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/file/admin/upload', formData).then((response)=>{
        Loading.hide();
        let resp = response.data;
        console.log("上传文件成功：", resp);
        _this.afterUpload(resp);
        $("#" + _this.inputId + "-input").val("");
      });
    },

    selectFile () {
      let _this = this;
      $("#"+_this.inputId+"-input").trigger("click");
    }
  }
}

</script>