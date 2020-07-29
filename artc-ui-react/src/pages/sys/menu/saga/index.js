import Constants from "../constants";
import {call, put, take} from "redux-saga/effects";
import axios from 'axios';

function* list() {
    let result = yield call(axios.get, "/core/menu");
    yield put({type: Constants.LIST, result: result, treeData: getTreeData(result), loading: false});
}

function getTreeData(menus) {
    let result = [];
    if (menus == null) return null;
    for (let i = 0; i < menus.length; i++) {
        let menu = menus[i];
        if (menu.type === 1) continue;
        let node = {value: menu.id, title: menu.name};
        let childrenMenus = getTreeData(menu.children);
        if (childrenMenus != null) {
            node.children = childrenMenus;
        }
        result.push(node);
    }
    return result;
}

function* loadMenu() {
    while(true) {
        yield take(Constants.LIST);
        yield call(list);
    }
}

function* addMenu() {
    while(true) {
        const {model} = yield take(Constants.ADD);
        yield call(axios.post, "/core/menu", {
            name: model.name,
            url: model.url,
            type: model.type,
            parentId: model.parentId ? model.parentId : "0",
            permission: model.permission
        })
        yield put({type: Constants.ADD, loading: false});
        yield put({type: Constants.VISIBLE_MODE, visible: false, mode: null});
        yield call(list);
    }
}

function* visibleMode() {
    while(true) {
        const {visible, mode, id} = yield take(Constants.VISIBLE_MODE);
        if(visible && mode && mode !== "add") {
            let data = yield call(axios.get, `/core/menu/${id}`);
            yield put({type: Constants.VIEW, data: data});
        }
    }
}


const MenuSagas = [
    loadMenu(),
    addMenu(),
    visibleMode()
]

export default MenuSagas;