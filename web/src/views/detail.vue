<template>
  <main role="main">
    <div className="album py-5 bg-light">
      <div className="container">
        <div className="row course-head">
          <div className="col-sm-6" id="cover-video-div">
            <img className="img-fluid" v-bind:src="course.image">
          </div>
          <div className="col-sm-6">
            <h1>{{ co urse.name }}</h1>
            <p className="course-head-item">
              <span><i className="fa fa-clock-o"></i> {{ (course.time) | formatSecond }}</span>
              <span>{{ CO URSE_LEVEL | optionKV(course.level) }}</span>
              <span><i className="fa fa-user"></i> {{ cou rse.enroll }}</span>
            </p>
            <p className="course-head-desc">{{ co urse.summary }}</p>
            <p className="course-head-price">
              <span className="price-now text-danger"><i className="fa fa-yen"></i>&nbsp;{{ course.price }}&nbsp;&nbsp;</span>
            </p>
            <p className="course-head-button-links">
              <a className="btn btn-lg btn-primary btn-shadow" href="javascript:;">立即报名</a>
            </p>
          </div>
        </div>
      </div>
    </div>

  </main>
</template>

<script>

export default {
  name: 'detail',
  data: function () {
    return {
      id: "",
      course: {},
      teacher: {},
      chapters: [],
      sections: [],
      COURSE_LEVEL: COURSE_LEVEL
    }
  },
  mounted() {
    let _this = this;
    _this.id = _this.$route.query.id;
    _this.findCourse();
  },
  methods: {
    findCourse() {
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/business/web/course/find/' + _this.id).then((response) => {
        let resp = response.data;
        _this.course = resp.content;
        _this.teacher = _this.course.teacher || {};
        _this.chapters = _this.course.chapters || [];
        _this.sections = _this.course.sections || [];
      })
    },
  }
}
</script>

<style>
.course-head {
}

.course-head h1 {
  font-size: 2rem;
  margin-bottom: 1.5rem;
}

.course-head-item span {
  margin-right: 1rem;
}

.course-head-desc {
  font-size: 1rem;
  color: #555
}

.course-head a {
}

.course-head-price {
  font-size: 2rem;
}

@media (max-width: 700px) {
  .course-head h1 {
    font-size: 1.5rem;
  }
}
</style>
