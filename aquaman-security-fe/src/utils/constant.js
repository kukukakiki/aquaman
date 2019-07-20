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
    }],
    deptType: [{
      value: 'group',
      label: '集团'
    }, {
      value: 'head',
      label: '总公司'
    }, {
      value: 'branch',
      label: '分公司'
    }, {
      value: 'child',
      label: '子公司'
    }, {
      value: 'dept',
      label: '部门'
    }, {
      value: 'team',
      label: '组'
    }]
  })
}

/**
 * 通过key值获取label值
 * @param {*} key
 */
export function getLabel(val, key) {
  var label = ''
  switch (val) {
    case 'status':
      label = options().status.filter(item => item.value === key)
      break
    case 'type':
      label = options().type.filter(item => item.value === key)
      break
    case 'display':
      label = options().display.filter(item => item.value === key)
      break
    case 'register':
      label = options().register.filter(item => item.value === key)
      break
    case 'deptType':
      label = options().deptType.filter(item => item.value === key)
      break
  }
  if (isCollectionNotEmpty(label)) {
    return label[0].label
  }
  return label
}
