Toast = {
  success: function (message) {
    Swal.fire({
      position: '',
      icon: 'success',
      title: message,
      showConfirmButton: false,
      timer: 1000
    })
  },

  error: function (message) {
    Swal.fire({
      position: '',
      icon: 'error',
      title: message,
      showConfirmButton: false,
      timer: 1000
    })
  },

  warning: function (message) {
    Swal.fire({
      position: '',
      icon: 'warning',
      title: message,
      showConfirmButton: false,
      timer: 1000
    })
  }
};