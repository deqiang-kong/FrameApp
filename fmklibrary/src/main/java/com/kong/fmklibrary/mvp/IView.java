/**
 * Copyright 2017 JessYan
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kong.fmklibrary.mvp;

/**
 * ================================================
 * 框架要求框架中的每个 View 都需要实现此类,以满足规范
 *
 * @see <a href="https://github.com/JessYanCoding/MVPArms/wiki#2.4.2">View wiki 官方文档</a>
 * Created by JessYan on 4/22/2016
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public interface IView {


    /**
     * 显示等待效果
     */
    void showWaitingView();

    /**
     * 请求失败，重试
     */
    void againWaitingView();

    /**
     * 取消等待效果
     */
    void dismissWaitingView();

    /**
     * 显示信息
     */
    void showMessage(String message);

//    /**
//     * 跳转 {@link Activity}
//     */
//    void launchActivity(Intent intent);
//
//    /**
//     * 杀死自己
//     */
//    void killMyself();
}
