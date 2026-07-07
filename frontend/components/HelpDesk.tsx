import React, { useState } from 'react';

interface HelpDeskProps {
  onBack: () => void;
  t: (key: string) => string;
}

const HelpDesk: React.FC<HelpDeskProps> = ({ onBack, t }) => {
  const [formData, setFormData] = useState({
    title: '',
    priority: 'Normal',
    email: '',
    description: ''
  });
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [submitted, setSubmitted] = useState(false);

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    setIsSubmitting(true);

    setTimeout(() => {
      const subject = encodeURIComponent(`[Support Request] ${formData.title}`);
      const body = encodeURIComponent(
        `Priority: ${formData.priority}\n` +
        `Contact Email: ${formData.email || 'Not provided'}\n\n` +
        `Description:\n${formData.description}`
      );
      
      const mailtoLink = `mailto:jacebedo@saniikos.com?subject=${subject}&body=${body}`;
      window.location.href = mailtoLink;
      
      setIsSubmitting(false);
      setSubmitted(true);
    }, 1000);
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  if (submitted) {
    return (
      <div className="flex-1 overflow-y-auto p-8 lg:p-12 flex flex-col items-center justify-center animate-in fade-in zoom-in duration-500">
        <div className="max-w-md w-full bg-white p-10 rounded-3xl shadow-2xl border border-slate-100 text-center">
          <div className="w-20 h-20 bg-green-50 text-green-600 rounded-full flex items-center justify-center mx-auto mb-6 shadow-inner">
            <span className="material-symbols-outlined text-4xl font-bold">check_circle</span>
          </div>
          <h2 className="text-3xl font-black text-slate-900 mb-4">{t('helpdesk.success.title')}</h2>
          <p className="text-slate-500 font-medium mb-10">
            {t('helpdesk.success.desc')}
          </p>
          <div className="space-y-4">
            <button 
              onClick={onBack}
              className="w-full bg-primary hover:bg-primary-light text-white font-bold py-4 rounded-2xl transition-all shadow-lg shadow-primary/20"
            >
              {t('helpdesk.back')}
            </button>
            <button 
              onClick={() => setSubmitted(false)}
              className="w-full bg-slate-50 hover:bg-slate-100 text-slate-600 font-bold py-4 rounded-2xl transition-all"
            >
              {t('helpdesk.success.another')}
            </button>
          </div>
        </div>
      </div>
    );
  }

  return (
    <div className="flex-1 overflow-y-auto p-6 lg:p-12 animate-in fade-in slide-in-from-bottom-4 duration-500">
      <div className="max-w-3xl mx-auto">
        <div className="mb-10 flex flex-col md:flex-row md:items-end justify-between gap-4">
          <div>
            <button 
              onClick={onBack}
              className="flex items-center gap-2 text-primary font-bold text-sm mb-4 hover:gap-3 transition-all group"
            >
              <span className="material-symbols-outlined text-sm font-black group-hover:-translate-x-1 transition-transform">arrow_back</span>
              {t('helpdesk.back')}
            </button>
            <h2 className="text-4xl font-black tracking-tight text-slate-900 mb-3">{t('helpdesk.title')}</h2>
            <p className="text-slate-500 text-lg font-medium max-w-xl">
              {t('helpdesk.subtitle')}
            </p>
          </div>
          <div className="bg-accent/10 px-4 py-2 rounded-xl border border-accent/20 flex items-center gap-2">
            <span className="material-symbols-outlined text-accent text-xl">info</span>
            <span className="text-primary text-xs font-bold uppercase tracking-wide">{t('helpdesk.response_time')}</span>
          </div>
        </div>

        <form onSubmit={handleSubmit} className="bg-white border border-slate-200 rounded-3xl shadow-xl shadow-slate-200/50 overflow-hidden">
          <div className="p-8 lg:p-10 space-y-8">
            <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
              <div className="md:col-span-2">
                <label className="block text-xs font-black uppercase tracking-widest text-slate-400 mb-3 ml-1">
                  {t('helpdesk.form.title')} <span className="text-red-500">*</span>
                </label>
                <input 
                  required
                  type="text"
                  name="title"
                  value={formData.title}
                  onChange={handleInputChange}
                  placeholder="..."
                  className="w-full bg-slate-50 border-slate-200 focus:border-primary/20 focus:ring-4 focus:ring-primary/5 rounded-2xl py-3.5 px-5 text-slate-900 placeholder:text-slate-300 font-medium transition-all"
                />
              </div>

              <div>
                <label className="block text-xs font-black uppercase tracking-widest text-slate-400 mb-3 ml-1">
                  {t('helpdesk.form.priority')}
                </label>
                <select 
                  name="priority"
                  value={formData.priority}
                  onChange={handleInputChange}
                  className="w-full bg-slate-50 border-slate-200 focus:border-primary/20 focus:ring-4 focus:ring-primary/5 rounded-2xl py-3.5 px-5 text-slate-900 font-medium transition-all"
                >
                  <option value="Low">{t('helpdesk.priority.low')}</option>
                  <option value="Normal">{t('helpdesk.priority.normal')}</option>
                  <option value="High">{t('helpdesk.priority.high')}</option>
                  <option value="Urgent / Critical">{t('helpdesk.priority.urgent')}</option>
                </select>
              </div>

              <div>
                <label className="block text-xs font-black uppercase tracking-widest text-slate-400 mb-3 ml-1">
                  {t('helpdesk.form.email')} <span className="text-slate-300">{t('helpdesk.form.email_opt')}</span>
                </label>
                <input 
                  type="email"
                  name="email"
                  value={formData.email}
                  onChange={handleInputChange}
                  placeholder="your.email@company.com"
                  className="w-full bg-slate-50 border-slate-200 focus:border-primary/20 focus:ring-4 focus:ring-primary/5 rounded-2xl py-3.5 px-5 text-slate-900 placeholder:text-slate-300 font-medium transition-all"
                />
              </div>

              <div className="md:col-span-2">
                <label className="block text-xs font-black uppercase tracking-widest text-slate-400 mb-3 ml-1">
                  {t('helpdesk.form.desc')} <span className="text-red-500">*</span>
                </label>
                <textarea 
                  required
                  rows={5}
                  name="description"
                  value={formData.description}
                  onChange={handleInputChange}
                  placeholder="..."
                  className="w-full bg-slate-50 border-slate-200 focus:border-primary/20 focus:ring-4 focus:ring-primary/5 rounded-2xl py-3.5 px-5 text-slate-900 placeholder:text-slate-300 font-medium transition-all resize-none"
                />
              </div>
            </div>
          </div>

          <div className="px-8 py-6 bg-slate-50 border-t border-slate-200 flex items-center justify-between gap-4">
            <p className="text-[10px] text-slate-400 font-bold uppercase tracking-widest">
              {t('helpdesk.form.mandatory')}
            </p>
            <button 
              disabled={isSubmitting}
              type="submit"
              className={`
                flex items-center gap-2 bg-primary hover:bg-primary-light text-white font-bold py-3.5 px-10 rounded-2xl transition-all shadow-lg shadow-primary/20
                ${isSubmitting ? 'opacity-70 cursor-not-allowed' : 'hover:scale-[1.02] active:scale-95'}
              `}
            >
              {isSubmitting ? (
                <>
                  <span className="w-5 h-5 border-2 border-white/30 border-t-white rounded-full animate-spin"></span>
                  {t('helpdesk.form.processing')}
                </>
              ) : (
                <>
                  <span className="material-symbols-outlined text-xl">send</span>
                  {t('helpdesk.form.submit')}
                </>
              )}
            </button>
          </div>
        </form>
        
        <div className="mt-12 grid grid-cols-1 sm:grid-cols-3 gap-6">
          <div className="bg-white p-6 rounded-2xl border border-slate-200 flex items-center gap-4">
            <div className="w-10 h-10 bg-blue-50 text-blue-600 rounded-xl flex items-center justify-center">
              <span className="material-symbols-outlined">call</span>
            </div>
            <div>
              <p className="text-xs font-black text-slate-400 uppercase tracking-tight">{t('helpdesk.info.ext')}</p>
              <p className="text-sm font-bold text-slate-900">int. 8706</p>
            </div>
          </div>
          <div className="bg-white p-6 rounded-2xl border border-slate-200 flex items-center gap-4">
            <div className="w-10 h-10 bg-orange-50 text-orange-600 rounded-xl flex items-center justify-center">
              <span className="material-symbols-outlined">mail</span>
            </div>
            <div>
              <p className="text-xs font-black text-slate-400 uppercase tracking-tight">{t('helpdesk.info.email')}</p>
              <p className="text-sm font-bold text-slate-900">support@saniikos.com</p>
            </div>
          </div>
          <div className="bg-white p-6 rounded-2xl border border-slate-200 flex items-center gap-4">
            <div className="w-10 h-10 bg-green-50 text-green-600 rounded-xl flex items-center justify-center">
              <span className="material-symbols-outlined">live_help</span>
            </div>
            <div>
              <p className="text-xs font-black text-slate-400 uppercase tracking-tight">{t('helpdesk.info.kb')}</p>
              <p className="text-sm font-bold text-slate-900">wiki.company.com</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default HelpDesk;