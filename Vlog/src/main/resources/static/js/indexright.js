new Vue({
    el: '#app',
    data: {
        timer: null, //定时器
        active_index: 0, //当前轮播下标
        is_prev: false, //是否点击上一张(控制吃豆人朝向)
        // 轮播图数据(json格式)
        list: [{
            title: '个人博客主页',
            image: '../images/01.jpg',
            bottom_color: '#DCB331',
            url: 'img1'
        },
        {
            title: '自定义标题二',
            image: '../images/02.jpg',
            bottom_color: '#2c84cd',
            url: 'img2'
        },
        {
            title: '自定义标题三',
            image: '../images/03.jpg',
            bottom_color: '#0DCBD0',
            url: 'img3'
        },
        {
            title: '自定义标题四',
            image: '../images/04.jpg',
            bottom_color: '#623E26',
            url: 'img4'
        },
        {
            title: '自定义标题五',
            image: '../images/05.jpg',
            bottom_color: '#0B6697',
            url: 'img5'
        }]
    },
    methods: {
        // 停止轮播
        stopAutoPlay() {
            // 清空定时器
            for (let i = 0; i <= this.timer; i++) {
                clearInterval(i);
            }
        },
        // 开始轮播
        startAutoPlay() {
            // 停止轮播
            this.stopAutoPlay();
            let _t = this;
            this.timer = setInterval(function () {
                _t.active_index++;
                if (_t.active_index > _t.list.length - 1) {
                    _t.active_index = 0;
                }
                _t.is_prev = false;
                _t.changeBanner(_t.active_index);
            }, 3000);
        },
        // 切换banner 参数:index=轮播下标(点击上一张,下一张按钮时,该值为-1;点击指示器时,该值为对应的轮播下标),is_prev=是否上一张(true为上一张,false为下一张)
        changeBanner(index, is_prev) {
            if (index >= 0) {
                // 点击指示器时
                // 默认是下一张,吃豆人向右
                this.is_prev = false;
                if (index < this.active_index) {
                    // 点击时轮播下标小于当前轮播下标时,则为上一张,吃豆人向左
                    this.is_prev = true;
                }
                // 设置当前轮播下标
                this.active_index = index;
            } else {
                // 点击按钮时
                if (is_prev) {
                    // 上一张
                    this.active_index--;
                    if (this.active_index < 0) {
                        this.active_index = this.list.length - 1;
                    }
                } else {
                    // 下一张
                    this.active_index++;
                    if (this.active_index > this.list.length - 1) {
                        this.active_index = 0;
                    }
                }
                // 指明上一张或下一张
                this.is_prev = is_prev;
            }
            // --m-left,--c-color是css的自定义属性,可通过var函数进行调用
            // 设置偏移量以达到显示指定图片的目的
            document.querySelector('.img-boxx').style.setProperty('--m-left', this.active_index);
            // 设置图片底部的渐变效果
            document.querySelector('.bottom-boxx').style.setProperty('--b-color', this.list[this.active_index].bottom_color);
        }
    },
    mounted() {
        // 初始化,自动轮播
        this.startAutoPlay();
    }
})