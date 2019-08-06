/**
 * 控制台输出基本信息
 */
const showLogger = process.env.CONSOLE_LOGGER
exports.install = function(Vue, options) {
  Vue.prototype.loggerInfo = function(message, ...val) {
    if (showLogger) {
      console.log(message, val)
    }
  }
}
