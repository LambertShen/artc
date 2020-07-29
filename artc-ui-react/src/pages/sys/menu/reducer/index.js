import Constants from "../constants";

export const menuInfo = (state = {}, action) => {
    switch (action.type) {
        case Constants.LIST:
            return {
                ...state,
                dataSource: action.result,
                treeData: action.treeData,
                loading: action.loading,
            }

        default:
            return state;
    }
}

export const menuFormInfo = (state = {}, action) => {
    switch (action.type) {
        case Constants.ADD:
            return {
                ...state,
                loading: action.loading,
            }
        case Constants.VISIBLE_MODE:
            return {
                ...state,
                visible: action.visible,
                mode: action.mode,
                id: action.id
            }
        case Constants.VIEW:
            return {
                ...state,
                data: action.data
            }
        default:
            return state;
    }
}