### DOM 更新时机
当你修改了响应式状态时，DOM 会被自动更新。但是需要注意的是，DOM 更新不是同步的。Vue 会在“next tick”更新周期中缓冲所有状态的修改，以确保不管你进行了多少次状态修改，每个组件都只会被更新一次。

要等待 DOM 更新完成后再执行额外的代码，可以使用 nextTick() 全局 API：

```js
import { nextTick } from 'vue'

async function increment() {
count.value++
await nextTick()
// 现在 DOM 已经更新了
}
```
