'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  BASE_API: '"http://aquaman.leerzoom.com:8001"',
  HEANER_AUTHO_BASIC: '"Basic YXF1YW1hbjphcXVhbWFuMjAxOQ=="'
})
