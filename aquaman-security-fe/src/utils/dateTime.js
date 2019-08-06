exports.install = function(Vue, options) {
  Vue.prototype.defaultFormateDate = function(cellValue) {
    if (cellValue !== undefined && cellValue !== null && cellValue !== '') {
      const dateTime = cellValue + ''
      if (dateTime.length === 14) {
        const fullYear = dateTime.substring(0, 4)
        const month = dateTime.substring(4, 6)
        const day = dateTime.substring(6, 8)
        const hours = dateTime.substring(8, 10)
        const minutes = dateTime.substring(10, 12)
        const seconds = dateTime.substring(12, 14)
        return fullYear + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds
      } else {
        return '暂无时间'
      }
    } else {
      return '暂无时间'
    }
  }
}
