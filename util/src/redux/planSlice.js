import { createSlice } from "@reduxjs/toolkit"



// 초기 State 선언
const initialSliceState = {
  plans: null,
  todos: {

  },
  todosPeriod: {

  }
}

const modifyPlanSlice = createSlice({
  name: 'modifyPlanSlice',
  initialState: initialSliceState,
  reducers: {
    responsePlans(state, action) {
      const parsedPayload = JSON.parse(action.payload)
      state.plans = parsedPayload
    },
    responseTodos(state, action) {
      const parsedPayload = JSON.parse(action.payload)
      state.todos[parsedPayload.goalId] = parsedPayload.data
    },
    responseTodosPeriod(state, action) {
      const parsedPayload = JSON.parse(action.payload)
      state.todosPeriod = parsedPayload[0]
    },
    responseTodoPeriod(state, action) {
      const parsedPayload = JSON.parse(action.payload)
      state.todosPeriod[parsedPayload.goalId] = parsedPayload.data[parsedPayload.goalId]
    },
    deletePlan(state, action) {
      const parsedPayload = JSON.parse(action.payload)
      state.plans.splice(parsedPayload, 1)
    },
    modifyStartDate(state, action) {
      const parsedPayload = JSON.parse(action.payload)
      state.plans[parsedPayload.idx].startDate = parsedPayload.updatedDate
    },
    modifyEndDate(state, action) {
      const parsedPayload = JSON.parse(action.payload)
      state.plans[parsedPayload.idx].endDate = parsedPayload.updatedDate
    },

  }
})

// Redux를 Slice 단위로 분할하였으므로 아래의 구문 또한 작성
export const modifyPlanSliceActions = modifyPlanSlice.actions

export default modifyPlanSlice.reducer