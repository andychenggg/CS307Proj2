<template>
    <div class="wrapper">
        <ForumHeader></ForumHeader>
        <div class="content-wrapper">
            <div class="leftTab-wrapper">
                <LeftTab></LeftTab>
            </div>
            <div class="postContainer-wrapper">
                <PostContainer></PostContainer>
            </div>
        </div>
    </div>
</template>


<script>
import ForumHeader from './ForumHeader.vue';
import LeftTab from './LeftTab.vue';
import PostContainer from './PostContainer.vue';

export default {
    components: {
        ForumHeader,
        LeftTab,
        PostContainer
    }
};
</script>
<style>
body{
    margin: 0;
    padding: 0;
    width: 100%;
    height: 100%;
}

.wrapper{
    width: 100%;
    height: 100%;
}

.content-wrapper {
    display: flex;
    flex-direction: row;
    height: 100vh;
    width: 100%;
}

.leftTab-wrapper{
    width: 30%;
    height: 100%;
}

.postContainer-wrapper{
    width: 70%;
    height: 100%;
}


</style>