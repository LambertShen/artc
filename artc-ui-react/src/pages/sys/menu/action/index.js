import Constants from "../constants";

export const setVisibleMode_action = (visible, mode = null, id= -1) => ({
    type: Constants.VISIBLE_MODE,
    visible: visible,
    mode: mode,
    id: id
})

export const setMode_action = (mode) => ({
    type: Constants.MODE,
    mode: mode
})

export const add_action = (model) => ({
    type: Constants.ADD,
    model: model,
    loading: true
})

export const del_action = (id) => ({
    type: Constants.DEL,
    id: id
})

export const edit_action = (model) => ({
    type: Constants.EDIT,
    model: model
})

export const view_action = (id) => ({
    type: Constants.VIEW,
    id: id
})

export const list_action = () => ({
    type: Constants.LIST,
    result: [],
    loading: true
})