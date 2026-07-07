import React, { useState } from 'react';
import { Language } from '../types';

interface AuthProps {
  onLogin: () => void;
  t: (key: string) => string;
  lang: Language;
  onLangChange: (lang: Language) => void;
}

const Auth: React.FC<AuthProps> = ({ onLogin, t, lang, onLangChange }) => {
  const [view, setView] = useState<'login' | 'register'>('login');
  const [formData, setFormData] = useState({
    username: '',
    password: '',
    fullname: '',
    department: '',
    email: ''
  });
  const [submitted, setSubmitted] = useState(false);
  const [showLangMenu, setShowLangMenu] = useState(false);

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const handleLoginSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    // Simulate login
    onLogin();
  };

  const handleRegisterSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    const subject = encodeURIComponent(`[User Creation Request] ${formData.fullname}`);
    const body = encodeURIComponent(
      `Full Name: ${formData.fullname}\n` +
      `Department: ${formData.department}\n` +
      `Email Address: ${formData.email}`
    );
    const mailtoLink = `mailto:jacebedo@saniikos.com?subject=${subject}&body=${body}`;
    window.location.href = mailtoLink;
    setSubmitted(true);
  };

  const languages: { code: Language; name: string; flag: string }[] = [
    { code: 'en', name: 'English', flag: '🇺🇸' },
    { code: 'es', name: 'Español', flag: '🇪🇸' },
    { code: 'el', name: 'Ελληνικά', flag: '🇬🇷' },
  ];

  if (submitted) {
    return (
      <div className="min-h-screen bg-slate-50 flex items-center justify-center p-4">
        <div className="max-w-md w-full bg-white p-10 rounded-3xl shadow-2xl border border-slate-100 text-center animate-in fade-in zoom-in duration-500">
          <div className="w-20 h-20 bg-green-50 text-green-600 rounded-full flex items-center justify-center mx-auto mb-6 shadow-inner">
            <span className="material-symbols-outlined text-4xl font-bold">check_circle</span>
          </div>
          <h2 className="text-3xl font-black text-slate-900 mb-4">{t('auth.register.success.title')}</h2>
          <p className="text-slate-500 font-medium mb-10 leading-relaxed">
            {t('auth.register.success.desc')}
          </p>
          <button 
            onClick={() => {
              setSubmitted(false);
              setView('login');
            }}
            className="w-full bg-primary hover:bg-primary-light text-white font-bold py-4 rounded-2xl transition-all shadow-lg shadow-primary/20"
          >
            {t('auth.register.back')}
          </button>
        </div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-slate-50 flex flex-col items-center justify-center p-4 relative">
      {/* Top Header Mock for Language Toggle */}
      <div className="absolute top-6 right-6">
        <div className="relative">
          <button 
            onClick={() => setShowLangMenu(!showLangMenu)}
            className="flex items-center gap-2 px-3 py-2 rounded-xl border border-slate-100 bg-white hover:bg-slate-50 hover:border-slate-200 transition-all shadow-sm"
          >
            <span className="text-lg leading-none">{languages.find(l => l.code === lang)?.flag}</span>
            <span className="text-xs font-bold text-slate-700 uppercase tracking-wider">{lang}</span>
            <span className="material-symbols-outlined text-slate-400 text-lg">language</span>
          </button>
          {showLangMenu && (
            <>
              <div className="fixed inset-0 z-10" onClick={() => setShowLangMenu(false)} />
              <div className="absolute right-0 mt-2 w-48 bg-white border border-slate-200 rounded-2xl shadow-2xl z-20 overflow-hidden animate-in fade-in slide-in-from-top-2 duration-200">
                <div className="p-2 space-y-1">
                  {languages.map((l) => (
                    <button
                      key={l.code}
                      onClick={() => {
                        onLangChange(l.code);
                        setShowLangMenu(false);
                      }}
                      className={`w-full flex items-center gap-3 px-3 py-2.5 rounded-xl text-sm transition-colors ${lang === l.code ? 'bg-primary text-white font-bold' : 'text-slate-600 hover:bg-slate-50'}`}
                    >
                      <span className="text-base">{l.flag}</span>
                      <span>{l.name}</span>
                      {lang === l.code && <span className="material-symbols-outlined ml-auto text-sm">check</span>}
                    </button>
                  ))}
                </div>
              </div>
            </>
          )}
        </div>
      </div>

      <div className="w-full max-w-md animate-in fade-in slide-in-from-bottom-4 duration-500">
        {/* Logo */}
        <div className="flex flex-col items-center mb-8">
          <div className="bg-primary rounded-2xl p-4 text-white shadow-xl shadow-primary/20 mb-4">
            <span className="material-symbols-outlined text-4xl">hub</span>
          </div>
          <h1 className="text-3xl font-black text-primary tracking-tight">Saniikos</h1>
          <p className="text-slate-400 text-[11px] font-bold uppercase tracking-[0.2em] mt-2">Corporate Portal</p>
        </div>

        <div className="bg-white border border-slate-200 rounded-[32px] shadow-2xl shadow-slate-200/50 overflow-hidden">
          <div className="p-8 lg:p-10">
            {view === 'login' ? (
              <>
                <div className="text-center mb-10">
                  <h2 className="text-2xl font-black text-slate-900 mb-2">{t('auth.login.title')}</h2>
                  <p className="text-slate-500 font-medium">{t('auth.login.subtitle')}</p>
                </div>

                <form onSubmit={handleLoginSubmit} className="space-y-6">
                  <div>
                    <label className="block text-[10px] font-black uppercase tracking-widest text-slate-400 mb-2 ml-1">
                      {t('auth.login.username')}
                    </label>
                    <div className="relative group">
                      <span className="material-symbols-outlined absolute left-4 top-1/2 -translate-y-1/2 text-slate-400 text-lg transition-colors group-focus-within:text-primary">person</span>
                      <input 
                        required
                        type="text"
                        name="username"
                        value={formData.username}
                        onChange={handleInputChange}
                        className="w-full bg-slate-50 border-transparent focus:bg-white focus:border-primary/20 focus:ring-4 focus:ring-primary/5 rounded-2xl py-3.5 pl-12 pr-5 text-slate-900 font-medium transition-all"
                        placeholder="john.doe"
                      />
                    </div>
                  </div>

                  <div>
                    <label className="block text-[10px] font-black uppercase tracking-widest text-slate-400 mb-2 ml-1">
                      {t('auth.login.password')}
                    </label>
                    <div className="relative group">
                      <span className="material-symbols-outlined absolute left-4 top-1/2 -translate-y-1/2 text-slate-400 text-lg transition-colors group-focus-within:text-primary">lock</span>
                      <input 
                        required
                        type="password"
                        name="password"
                        value={formData.password}
                        onChange={handleInputChange}
                        className="w-full bg-slate-50 border-transparent focus:bg-white focus:border-primary/20 focus:ring-4 focus:ring-primary/5 rounded-2xl py-3.5 pl-12 pr-5 text-slate-900 font-medium transition-all"
                        placeholder="••••••••"
                      />
                    </div>
                  </div>

                  <button 
                    type="submit"
                    className="w-full bg-primary hover:bg-primary-light text-white font-bold py-4 rounded-2xl transition-all shadow-lg shadow-primary/20 hover:scale-[1.02] active:scale-95"
                  >
                    {t('auth.login.submit')}
                  </button>
                </form>

                <div className="mt-8 pt-8 border-t border-slate-100 text-center">
                  <p className="text-sm text-slate-500 font-medium">
                    {t('auth.login.no_account')} {' '}
                    <button 
                      onClick={() => setView('register')}
                      className="text-primary font-bold hover:underline"
                    >
                      {t('auth.register.link')}
                    </button>
                  </p>
                </div>
              </>
            ) : (
              <>
                <div className="text-center mb-10">
                  <h2 className="text-2xl font-black text-slate-900 mb-2">{t('auth.register.title')}</h2>
                  <p className="text-slate-500 font-medium">{t('auth.register.subtitle')}</p>
                </div>

                <form onSubmit={handleRegisterSubmit} className="space-y-5">
                  <div>
                    <label className="block text-[10px] font-black uppercase tracking-widest text-slate-400 mb-2 ml-1">
                      {t('auth.register.fullname')}
                    </label>
                    <input 
                      required
                      type="text"
                      name="fullname"
                      value={formData.fullname}
                      onChange={handleInputChange}
                      className="w-full bg-slate-50 border-transparent focus:bg-white focus:border-primary/20 focus:ring-4 focus:ring-primary/5 rounded-2xl py-3.5 px-5 text-slate-900 font-medium transition-all"
                      placeholder="Alex Johnson"
                    />
                  </div>

                  <div>
                    <label className="block text-[10px] font-black uppercase tracking-widest text-slate-400 mb-2 ml-1">
                      {t('auth.register.dept')}
                    </label>
                    <input 
                      required
                      type="text"
                      name="department"
                      value={formData.department}
                      onChange={handleInputChange}
                      className="w-full bg-slate-50 border-transparent focus:bg-white focus:border-primary/20 focus:ring-4 focus:ring-primary/5 rounded-2xl py-3.5 px-5 text-slate-900 font-medium transition-all"
                      placeholder="IT Operations"
                    />
                  </div>

                  <div>
                    <label className="block text-[10px] font-black uppercase tracking-widest text-slate-400 mb-2 ml-1">
                      {t('auth.register.email')}
                    </label>
                    <input 
                      required
                      type="email"
                      name="email"
                      value={formData.email}
                      onChange={handleInputChange}
                      className="w-full bg-slate-50 border-transparent focus:bg-white focus:border-primary/20 focus:ring-4 focus:ring-primary/5 rounded-2xl py-3.5 px-5 text-slate-900 font-medium transition-all"
                      placeholder="alex.j@company.com"
                    />
                  </div>

                  <button 
                    type="submit"
                    className="w-full bg-primary hover:bg-primary-light text-white font-bold py-4 rounded-2xl transition-all shadow-lg shadow-primary/20 hover:scale-[1.02] active:scale-95 mt-4"
                  >
                    {t('auth.register.submit')}
                  </button>

                  <button 
                    type="button"
                    onClick={() => setView('login')}
                    className="w-full text-slate-500 text-sm font-bold hover:text-slate-900 transition-colors py-2"
                  >
                    {t('auth.register.back')}
                  </button>
                </form>
              </>
            )}
          </div>
        </div>

        {/* Support Link Mock */}
        <p className="mt-8 text-center text-[11px] text-slate-400 font-bold uppercase tracking-widest">
          Saniikos Corporate Portal © 2026
        </p>
      </div>
    </div>
  );
};

export default Auth;