<template>
  <div>
    <div className="space-12"></div>
    <div className="row">
      <div className="col-xs-12">
        <div className="text-center">
          <span className="btn btn-app btn-sm btn-light no-hover">
              <span className="line-height-1 bigger-170 blue"> 518 </span>
              <br>
              <span className="line-height-1 smaller-90"> 课程 </span>
          </span>
          <span className="btn btn-app btn-sm btn-yellow no-hover">
              <span className="line-height-1 bigger-170"> 6,321 </span>

              <br>
              <span className="line-height-1 smaller-90"> 大章 </span>
          </span>

          <span className="btn btn-app btn-sm btn-pink no-hover">
              <span className="line-height-1 bigger-170"> 4,182 </span>

              <br>
              <span className="line-height-1 smaller-90"> 小节 </span>
          </span>

          <span className="btn btn-app btn-sm btn-success no-hover">
              <span className="line-height-1 bigger-170"> 5.23 </span>

              <br>
              <span className="line-height-1 smaller-90"> 会员(万) </span>
          </span>

          <span className="btn btn-app btn-sm btn-primary no-hover">
              <span className="line-height-1 bigger-170"> 12.76 </span>

              <br>
              <span className="line-height-1 smaller-90"> 报名(万) </span>
          </span>

          <span className="btn btn-app btn-sm btn-grey no-hover">
              <span className="line-height-1 bigger-170"> 32.96 </span>

              <br>
              <span className="line-height-1 smaller-90"> 评论(万) </span>
          </span>

        </div>

        <div className="space-12"></div>

        <div className="hr hr2 hr-double"></div>

        <div className="space-12"></div>

      </div>

      <div className="col-sm-12 infobox-container">
        <div className="infobox infobox-green">
          <div className="infobox-icon">
            <i className="ace-icon fa fa-comments"></i>
          </div>

          <div className="infobox-data">
            <span className="infobox-data-number">332</span>
            <div className="infobox-content">新增评论</div>
          </div>

          <div className="stat stat-success">8%</div>
        </div>

        <div className="infobox infobox-blue">
          <div className="infobox-icon">
            <i className="ace-icon fa fa-twitter"></i>
          </div>

          <div className="infobox-data">
            <span className="infobox-data-number">58</span>
            <div className="infobox-content">新增注册会员</div>
          </div>

          <div className="badge badge-success">
            +10%
            <i className="ace-icon fa fa-arrow-up"></i>
          </div>
        </div>

        <div className="infobox infobox-pink">
          <div className="infobox-icon">
            <i className="ace-icon fa fa-shopping-cart"></i>
          </div>

          <div className="infobox-data">
            <span className="infobox-data-number">108</span>
            <div className="infobox-content">课程报名</div>
          </div>
          <div className="stat stat-important">4%</div>
        </div>

        <div className="space-12"></div>

        <div className="hr hr2 hr-double"></div>

        <div className="space-12"></div>

      </div>

    </div><!-- /.row -->
  </div>
</template>

<script>
export default {
  name: "welcome",
  mounted: function () {
    // sidebar激活样式方法一
    // this.$parent.activeSidebar("welcome-sidebar");
  },
  methods: {}
}
</script>
