<template>
  <main role="main">

    <section className="jumbotron text-center">
      <div className="container">
        <h1>在线视频课程平台</h1>
        <p className="lead text-muted m-3">
          知识付费时代刚刚起步，在这个领域有很多的发展机会。
        </p>
        <p>
          <a href="#" className="btn btn-primary my-2 p-3 font-weight-bold">点击进入所有课程</a>
        </p>
      </div>
    </section>

    <div className="album py-5 bg-light">
      <div className="container">
        <div className="title1">最新上线</div>
        <div className="row">
          <div v-for="o in news" className="col-md-4">
            <div className="card mb-4 shadow-sm course">
              <img className="img-fluid" v-bind:src="o.image">
              <div className="card-body">
                <h4 className="">{{ o.name }}</h4>
                <p className="card-text">{{ o.summary }}</p>
                <div className="d-flex justify-content-between align-items-center">
                  <div className="btn-group">
                    <button type="button" className="btn btn-sm btn-outline-secondary">课程详情</button>
                  </div>
                  <div className="text-muted">
                    <span className="badge badge-info"><i className="fa fa-yen"
                                                          aria-hidden="true"></i>&nbsp;{{ o.price }}</span>&nbsp;
                    <span className="badge badge-info"><i className="fa fa-user" aria-hidden="true"></i>&nbsp;123</span>&nbsp;
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <hr>

        <div className="title2">好课推荐</div>
        <div className="row">
          <div v-for="o in news" className="col-md-4">
            <div className="card mb-4 shadow-sm course">
              <img className="img-fluid" v-bind:src="o.image">
              <div className="card-body">
                <h4 className="">{{ o.name }}</h4>
                <p className="card-text">{{ o.summary }}</p>
                <div className="d-flex justify-content-between align-items-center">
                  <div className="btn-group">
                    <button type="button" className="btn btn-sm btn-outline-secondary">课程详情</button>
                  </div>
                  <div className="text-muted">
                    <span className="badge badge-info"><i className="fa fa-yen"
                                                          aria-hidden="true"></i>&nbsp;{{ o.price }}</span>&nbsp;
                    <span className="badge badge-info"><i className="fa fa-user" aria-hidden="true"></i>&nbsp;123</span>&nbsp;
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </main>
</template>

<script>

export default {
  name: 'index',
  data: function () {
    return {
      news: [],
    }
  },
  mounted() {
    let _this = this;
    _this.listNew();
  },
  methods: {
    /**
     * 查询新上好课
     */
    listNew() {
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/business/web/course/list-new').then((response) => {
        console.log("查询新上好课结果：", response);
        let resp = response.data;
        if (resp.success) {
          _this.news = resp.content;
        }
      }).catch((response) => {
        console.log("error：", response);
      })
    },
  }
}
</script>

<style>
.title1 {
  margin-bottom: 2rem;
  color: #fafafa;
  letter-spacing: 0;
  text-shadow: 0px 1px 0px #999, 0px 2px 0px #888, 0px 3px 0px #777, 0px 4px 0px #666, 0px 5px 0px #555, 0px 6px 0px #444, 0px 7px 0px #333, 0px 8px 7px #001135;
  font-size: 2rem;
}

.title2 {
  margin-bottom: 2rem;
  color: transparent;
  -webkit-text-stroke: 1px black;
  letter-spacing: 0.04em;
  font-size: 2rem;
}

.course h4 {
  font-size: 1.25rem;
  margin: 15px 0;
}

.course .text-muted .badge {
  font-size: 1rem;
}
</style>
