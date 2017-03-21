package org.huihui.gank;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.huihui.gank.api.MeiZiModel;
import org.huihui.gank.base.activity.BaseMVPActivity;
import org.huihui.gank.databinding.ItemMeiziBinding;

import java.util.List;

import rx.Subscriber;

public class MainActivity extends BaseMVPActivity<MainDelegate> {

    private MeiZiModel mMeiZiModel;
    private int page = 1;
    private final int count = 10;
    private MeiZiAdaper mMeiZiAdaper;

    @Override
    protected void initVariable(Bundle savedInstanceState) {
        mMeiZiModel = new MeiZiModel();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        getMeiZi();
    }

    private void getMeiZi() {
        mCompositeSubscription.add(mMeiZiModel.getMeiZi(count, page).subscribe(new Subscriber<MeiZiReponse>() {
            @Override
            public void onCompleted() {
                viewDelegate.stopRefresh();
            }

            @Override
            public void onError(Throwable e) {
                showToast("网络异常");
            }

            @Override
            public void onNext(MeiZiReponse meiZiReponse) {
                if (!meiZiReponse.error) {
                    if (mMeiZiAdaper==null) {
                        mMeiZiAdaper = new MeiZiAdaper(meiZiReponse.results);
                        viewDelegate.rlContent.setAdapter(mMeiZiAdaper);
                    }else{
                        mMeiZiAdaper.replaceData(meiZiReponse.results);
                        mMeiZiAdaper.notifyDataSetChanged();
                    }

                } else {
                    showToast("数据异常");
                }
                viewDelegate.stopRefresh();
            }
        }));
    }

    @Override
    public void initListener() {
        viewDelegate.srContent.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMeiZi();
            }
        });
    }

    public class MeiZiAdaper extends RecyclerView.Adapter<MeiZiHolder> {
        List<MeiZiReponse.ResultsBean> mResultsBean;

        public MeiZiAdaper(List<MeiZiReponse.ResultsBean> resultsBean) {
            mResultsBean = resultsBean;
        }

        @Override
        public MeiZiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MeiZiHolder((ItemMeiziBinding) DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.item_meizi, parent, false));
        }

        @Override
        public void onBindViewHolder(MeiZiHolder holder, int position) {
            holder.setData(mResultsBean.get(position));
        }

        @Override
        public int getItemCount() {
            return mResultsBean.size();
        }

        public void addData(List<MeiZiReponse.ResultsBean> resultsBean) {
            mResultsBean.addAll(resultsBean);
        }

        public void replaceData(List<MeiZiReponse.ResultsBean> resultsBean) {
            mResultsBean.clear();
            mResultsBean.addAll(resultsBean);
        }
    }

    public class MeiZiHolder extends RecyclerView.ViewHolder {
        private final ItemMeiziBinding viewDataBinding;

        public MeiZiHolder(ItemMeiziBinding itemView) {
            super(itemView.getRoot());
            viewDataBinding = itemView;
        }

        public void setData(MeiZiReponse.ResultsBean data) {
            viewDataBinding.setMeizi(data);
        }
    }
}
