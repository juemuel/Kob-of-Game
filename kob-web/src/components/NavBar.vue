<template>
    <!-- navbar-dark bg-dark 黑色 -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <!-- container窄 container-fluid宽 -->
        <div class="container"> 
            <!-- <a class="navbar-brand" href="/">King of Bot </a> -->
            <router-link class="navbar-brand" :to= "{name: 'home'}">King of Bot</router-link>
            <!-- 导航内容 -->
            <div class="collapse navbar-collapse" id="navbarText">
                <!-- 导航前部分 -->
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <!-- <a class="nav-link" aria-current="page" href="/pk/">对战</a> -->
                        <router-link :class="route_name == 'pk_index' ? 'nav-link active':'nav-link'" :to= "{name: 'pk_index'}">对战</router-link>
                    </li>
                    <li class="nav-item">
                        <!-- <a class="nav-link" aria-current="page" href="/record/">对局列表</a> -->
                        <router-link :class="route_name == 'record_index' ? 'nav-link active':'nav-link'" :to= "{name: 'record_index'}">对局列表</router-link>
                    </li>
                    <li class="nav-item">
                        <!-- <a class="nav-link" aria-current="page" href="/ranklist/">排行榜</a> -->
                        <router-link :class="route_name == 'ranklist_index' ? 'nav-link active':'nav-link'" :to= "{name: 'ranklist_index'}">排行榜</router-link>
                    </li>
                </ul>
                <!-- 导航后部分，用户下拉模块，用户登录时显示" -->
                <ul class="navbar-nav" v-if="$store.state.user.is_login">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            {{ $store.state.user.username }}
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li>
                                <!-- <a class="dropdown-item" href="/user/bot/">我的机器人</a> -->
                                <router-link :class="route_name == 'user_bot_index' ? 'dropdown-item active':'dropdown-item'" :to= "{name: 'user_bot_index'}">我的机器人</router-link>
                            </li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="#" @click="logout">退出</a></li>
                        </ul>
                    </li>
                </ul>
                <!-- 导航后部分，登录/注册，用户未登录时显示 -->
                <ul class="navbar-nav" v-else>
                    <li class="nav-item">
                        <router-link class="nav-link" :to="{name: 'user_account_login'}" role="button">
                            登陆
                        </router-link>
                    </li>
                    <li class="nav-item ">
                        <router-link class="nav-link" :to="{name: 'user_account_register'}" role="button">
                            注册
                        </router-link>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

</template>
<script>
import {useRoute} from 'vue-router'
import { computed } from 'vue';
import {useStore} from 'vuex';
export default{
    setup(){
        const store = useStore();
        const route = useRoute();
        let route_name = computed(()=> route.name)

        const logout = () =>{
            store.dispatch("logout");
        } 
        return {
            route_name,
            logout
        }
    }
}

</script>
<style></style>