LOCAL_KEY_REMEMBER_USER = "LOCAL_KEY_REMEMBER_USER"; // 课程管理页面点击章管理时，保存课程信息
//localStorage生命周期是永久，这意味着除非用户显示在浏览器提供的UI上清除localStorage信息，否则这些信息将永远存在。
//
// sessionStorage生命周期为当前窗口或标签页，一旦窗口或标签页被永久关闭了，那么所有通过sessionStorage存储的数据
//也就被清空了。
// 不同浏览器无法共享localStorage或sessionStorage中的信息。相同浏览器的不同页面间可以共享相同的 localStorage（
// 页面属于相同域名和端口），
// 但是不同页面或标签页间无法共享sessionStorage的信息。这里需要注意的是，页面及标 签页仅指顶级窗口，
// 如果一个标签页包含多个iframe标签且他们属于同源页面，那么他们之间是可以共享sessionStorage的。
LocalStorage = {
  get: function (key) {
    let v = localStorage.getItem(key);
    if (v && typeof(v) !== "undefined" && v !== "undefined") {
      return JSON.parse(v);
    }
  },
  set: function (key, data) {
    localStorage.setItem(key, JSON.stringify(data));
  },
  remove: function (key) {
    localStorage.removeItem(key);
  },
  clearAll: function () {
    localStorage.clear();
  }
};