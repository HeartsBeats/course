<template>
  <div>
    <button type="button" v-on:click="selectFile()" class="btn btn-white btn-default btn-round">
      <i class="ace-icon fa fa-upload"></i>
      {{ text }}
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
  name: 'big-file',
  props: {
    text: {
      default: "上传大文件"
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
    use: {
      default: ""
    },
  },
  data: function () {
    return {}
  },
  methods: {
    uploadFile() {
      let _this = this;
      let formData = new window.FormData();
      let file = _this.$refs.file.files[0];

      console.log(file);
      /*
        name: "test.mp4"
        lastModified: 1901173357457
        lastModifiedDate: Tue May 27 2099 14:49:17 GMT+0800 (中国标准时间) {}
        webkitRelativePath: ""
        size: 37415970
        type: "video/mp4"
      */

      // 生成文件标识，标识多次上传的是不是同一个文件
      let key = hex_md5(file);
      let key10 = parseInt(key, 16);
      let key62 = Tool._10to62(key10);
      console.log(key, key10, key62);
      /*
        d41d8cd98f00b204e9800998ecf8427e
        2.8194976848941264e+38
        6sfSqfOwzmik4A4icMYuUe
       */

      // 判断文件格式
      let suffixs = _this.suffixs;
      let fileName = file.name;
      let size = file.size;
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
      /**
       *  文件分片
       *  1.确定文件分片大小  : 以 20MB为一个分片
       *  2.分片索引  :  以第一块分片开始
       *  3.确定每片文件分片起始和结束位置: 当一个文件大小小于一个分片大小，则结束位置为文件大小
       *  4.从文件中截取一个分片大小的数据
       */
      let shardSize = 20 * 1024 * 1024;
      let shardIndex = 1;
      let start = shardIndex * shardSize;
      let end = Math.min(size,start+shardSize);
      let fileShard = file.slice(start,end);
      let shardTotal = Math.ceil(size/shardSize);

      // key："file"必须和后端controller参数名一致
      formData.append('shard', fileShard);
      formData.append('shardIndex', shardIndex);
      formData.append('shardSize', shardSize);
      formData.append('shardTotal', shardTotal);
      formData.append('use', _this.use);
      formData.append('name', file.name);
      formData.append('suffix', suffix);
      formData.append('size', size);
      formData.append("key",key62)
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/file/admin/upload', formData).then((response) => {
        Loading.hide();
        let resp = response.data;
        console.log("上传文件成功：", resp);
        _this.afterUpload(resp);
        $("#" + _this.inputId + "-input").val("");
      });
    },

    selectFile() {
      let _this = this;
      $("#" + _this.inputId + "-input").trigger("click");
    }
  }
}

</script>