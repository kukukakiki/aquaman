import { isCollectionNotEmpty } from '@/utils/validate'

export function options() {
  return Object({
    status: [{
      value: 'start',
      label: '启用'
    }, {
      value: 'stop',
      label: '停用'
    }],
    type: [{
      value: 'group',
      label: '菜单组'
    }, {
      value: 'menu',
      label: '菜单'
    }, {
      value: 'button',
      label: '按钮'
    }, {
      value: 'page',
      label: '弹出页'
    }],
    display: [{
      value: '1',
      label: '显示'
    }, {
      value: '0',
      label: '隐藏'
    }],
    register: [{
      value: 'web',
      label: '网页'
    }, {
      value: 'weixin',
      label: '微信'
    }, {
      value: 'alipay',
      label: '支付宝'
    }, {
      value: 'github',
      label: 'GitHub'
    }]
  })
}

/**
 * 通过key值获取label值
 * @param {*} key
 */
export function getLabel(key) {
  const label = options().status.filter(item => item.value === key)
  if (isCollectionNotEmpty(label)) {
    return label[0].label
  }
  return ''
}
